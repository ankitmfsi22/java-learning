package com.mindfire.banking.model;

import com.mindfire.banking.enums.AccountType;
import com.mindfire.banking.exception.BankException;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String accountNumber;
    private final Customer customer;
    private final List<Transaction> transactions = new ArrayList<>();
    private BigDecimal balance;

    protected Account(String accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = BigDecimal.ZERO;
    }

    public abstract AccountType getAccountType();

    public abstract BigDecimal getMinimumBalance();

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (!hasEnoughBalance(amount)) {
            throw new BankException("Insufficient balance");
        }
        balance = balance.subtract(amount);
    }

    public boolean hasEnoughBalance(BigDecimal amount) {
        BigDecimal balanceAfterWithdraw = balance.subtract(amount);
        return balanceAfterWithdraw.compareTo(getMinimumBalance()) >= 0;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Account account)) {
            return false;
        }
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + getAccountType() + " | Balance: INR " + balance.setScale(2);
    }
}