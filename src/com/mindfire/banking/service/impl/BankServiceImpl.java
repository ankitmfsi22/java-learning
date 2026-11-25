package com.mindfire.banking.service.impl;

import com.mindfire.banking.enums.AccountType;
import com.mindfire.banking.enums.TransactionStatus;
import com.mindfire.banking.enums.TransactionType;
import com.mindfire.banking.exception.BankException;
import com.mindfire.banking.model.Account;
import com.mindfire.banking.model.CurrentAccount;
import com.mindfire.banking.model.Customer;
import com.mindfire.banking.model.SavingsAccount;
import com.mindfire.banking.model.Transaction;
import com.mindfire.banking.service.BankService;
import com.mindfire.banking.storage.BankData;

import java.math.BigDecimal;
import java.util.List;

public class BankServiceImpl implements BankService {
    private final BankData bankData;

    public BankServiceImpl(BankData bankData) {
        this.bankData = bankData;
    }

    @Override
    public synchronized Customer createCustomer(String name, String email, String phone) {
        if (name.isBlank()) {
            throw new BankException("Name cannot be empty");
        }
        if (!email.matches("^[\\w.]+@[\\w]+\\.[a-zA-Z]{2,}$")) {
            throw new BankException("Invalid email");
        }
        if (!phone.matches("^[6-9]\\d{9}$")) {
            throw new BankException("Phone number must be 10 digits");
        }

        String normalizedEmail = email.toLowerCase();
        if (bankData.getCustomersByEmail().containsKey(normalizedEmail)) {
            throw new BankException("Email already registered");
        }

        Customer customer = new Customer(bankData.nextCustomerId(), name, normalizedEmail, phone);
        bankData.getCustomersById().put(customer.getCustomerId(), customer);
        bankData.getCustomersByEmail().put(normalizedEmail, customer);
        return customer;
    }

    @Override
    public synchronized Account createAccount(String customerId, AccountType accountType, BigDecimal initialDeposit) {
        Customer customer = bankData.getCustomersById().get(customerId);
        if (customer == null) {
            throw new BankException("Customer not found");
        }

        BigDecimal minimumBalance = accountType == AccountType.SAVINGS
                ? SavingsAccount.MINIMUM_BALANCE
                : CurrentAccount.MINIMUM_BALANCE;
        if (initialDeposit.compareTo(minimumBalance) < 0) {
            throw new BankException("Initial deposit must be at least INR " + minimumBalance.setScale(2));
        }

        String accountNumber = bankData.nextAccountNumber();
        Account account = accountType == AccountType.SAVINGS
                ? new SavingsAccount(accountNumber, customer)
                : new CurrentAccount(accountNumber, customer);

        bankData.getAccountsByNumber().put(accountNumber, account);
        customer.addAccount(account);

        if (initialDeposit.compareTo(BigDecimal.ZERO) > 0) {
            deposit(accountNumber, initialDeposit);
        }
        return account;
    }

    @Override
    public synchronized Transaction deposit(String accountNumber, BigDecimal amount) {
        checkAmount(amount);
        Account account = getAccount(accountNumber);

        account.deposit(amount);
        return saveTransaction(TransactionType.DEPOSIT, null, account, amount, TransactionStatus.SUCCESS);
    }

    @Override
    public synchronized Transaction withdraw(String accountNumber, BigDecimal amount) {
        checkAmount(amount);
        Account account = getAccount(accountNumber);

        if (!account.hasEnoughBalance(amount)) {
            saveTransaction(TransactionType.WITHDRAW, account, null, amount, TransactionStatus.FAILED);
            throw new BankException("Insufficient balance");
        }

        account.withdraw(amount);
        return saveTransaction(TransactionType.WITHDRAW, account, null, amount, TransactionStatus.SUCCESS);
    }

    @Override
    public synchronized Transaction transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        checkAmount(amount);
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new BankException("Source and destination account cannot be same");
        }

        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);

        if (!fromAccount.hasEnoughBalance(amount)) {
            saveTransaction(TransactionType.TRANSFER, fromAccount, toAccount, amount, TransactionStatus.FAILED);
            throw new BankException("Insufficient balance");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        return saveTransaction(TransactionType.TRANSFER, fromAccount, toAccount, amount, TransactionStatus.SUCCESS);
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account account = bankData.getAccountsByNumber().get(accountNumber);
        if (account == null) {
            throw new BankException("Account not found");
        }
        return account;
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return getAccount(accountNumber).getTransactions();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.copyOf(bankData.getCustomersById().values());
    }

    private void checkAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankException("Amount must be greater than 0");
        }
    }

    private Transaction saveTransaction(TransactionType type, Account fromAccount, Account toAccount,
                                        BigDecimal amount, TransactionStatus status) {
        String fromNumber = fromAccount == null ? null : fromAccount.getAccountNumber();
        String toNumber = toAccount == null ? null : toAccount.getAccountNumber();
        Transaction transaction = new Transaction(bankData.nextTransactionId(), type, fromNumber, toNumber, amount, status);

        if (fromAccount != null) {
            fromAccount.addTransaction(transaction);
        }
        if (toAccount != null) {
            toAccount.addTransaction(transaction);
        }
        return transaction;
    }
}