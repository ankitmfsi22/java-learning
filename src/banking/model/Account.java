package banking.model;

import banking.exception.BankException;

import java.math.BigDecimal;

public abstract class Account {
    private final String accountNumber;
    private final Customer customer;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
