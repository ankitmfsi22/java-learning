package banking.service;

import banking.model.Account;
import banking.model.AccountType;
import banking.model.Customer;
import banking.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    Customer createCustomer(String name, String email, String phone);

    Account createAccount(String customerId, AccountType accountType);

    Transaction deposit(String accountNumber, BigDecimal amount);

    Transaction withdraw(String accountNumber, BigDecimal amount);

    Transaction transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount);

    Account getAccount(String accountNumber);

    List<Transaction> getTransactionHistory(String accountNumber);

    List<Customer> getAllCustomers();
}
