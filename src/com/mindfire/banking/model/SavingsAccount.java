package com.mindfire.banking.model;

import com.mindfire.banking.enums.AccountType;

import java.io.Serial;
import java.math.BigDecimal;

public class SavingsAccount extends Account {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final BigDecimal MINIMUM_BALANCE = new BigDecimal("1000");

    public SavingsAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
}
