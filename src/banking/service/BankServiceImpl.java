package banking.service;

import banking.exception.BankException;
import banking.model.Account;
import banking.model.AccountType;
import banking.model.CurrentAccount;
import banking.model.Customer;
import banking.model.SavingsAccount;
import banking.model.Transaction;
import banking.model.TransactionStatus;
import banking.model.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BankServiceImpl implements BankService {
    private final Map<String, Customer> customers = new LinkedHashMap<>();
    private final Map<String, Account> accounts = new LinkedHashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    private int customerCounter = 1001;
    private int accountCounter = 10001;
    private int transactionCounter = 1001;

    @Override
    public Customer createCustomer(String name, String email, String phone) {
        if (name.isBlank()) {
            throw new BankException("Name cannot be empty");
        }
        if (!email.matches("^[\\w.]+@[\\w]+\\.[a-zA-Z]{2,}$")) {
            throw new BankException("Invalid email");
        }
        if (!phone.matches("^[6-9]\\d{9}$")) {
            throw new BankException("Phone number must be 10 digits");
        }

        String customerId = "CUST" + customerCounter++;
        Customer customer = new Customer(customerId, name, email, phone);
        customers.put(customerId, customer);
        return customer;
    }

    @Override
    public Account createAccount(String customerId, AccountType accountType) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new BankException("Customer not found");
        }

        String accountNumber = String.valueOf(accountCounter++);
        Account account;
        if (accountType == AccountType.SAVINGS) {
            account = new SavingsAccount(accountNumber, customer);
        } else {
            account = new CurrentAccount(accountNumber, customer);
        }

        accounts.put(accountNumber, account);
        return account;
    }

    @Override
    public Transaction deposit(String accountNumber, BigDecimal amount) {
        checkAmount(amount);
        Account account = getAccount(accountNumber);

        account.deposit(amount);
        return saveTransaction(TransactionType.DEPOSIT, null, accountNumber, amount, TransactionStatus.SUCCESS);
    }

    @Override
    public Transaction withdraw(String accountNumber, BigDecimal amount) {
        checkAmount(amount);
        Account account = getAccount(accountNumber);

        if (!account.hasEnoughBalance(amount)) {
            saveTransaction(TransactionType.WITHDRAW, accountNumber, null, amount, TransactionStatus.FAILED);
            throw new BankException("Insufficient balance");
        }

        account.withdraw(amount);
        return saveTransaction(TransactionType.WITHDRAW, accountNumber, null, amount, TransactionStatus.SUCCESS);
    }

    @Override
    public Transaction transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        checkAmount(amount);
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new BankException("Source and destination account cannot be same");
        }

        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);

        if (!fromAccount.hasEnoughBalance(amount)) {
            saveTransaction(TransactionType.TRANSFER, fromAccountNumber, toAccountNumber, amount, TransactionStatus.FAILED);
            throw new BankException("Insufficient balance");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        return saveTransaction(TransactionType.TRANSFER, fromAccountNumber, toAccountNumber, amount, TransactionStatus.SUCCESS);
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new BankException("Account not found");
        }
        return account;
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {
        getAccount(accountNumber);

        return transactions.stream()
                .filter(t -> accountNumber.equals(t.getFromAccount()) || accountNumber.equals(t.getToAccount()))
                .toList();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    private void checkAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankException("Amount must be greater than 0");
        }
    }

    private Transaction saveTransaction(TransactionType type, String fromAccount, String toAccount,
                                        BigDecimal amount, TransactionStatus status) {
        String transactionId = "TXN" + transactionCounter++;
        Transaction transaction = new Transaction(transactionId, type, fromAccount, toAccount, amount, status);
        transactions.add(transaction);
        return transaction;
    }
}