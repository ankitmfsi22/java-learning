package com.mindfire.banking.model;

import com.mindfire.banking.enums.AccountType;

import java.io.Serial;
import java.math.BigDecimal;

public class CurrentAccount extends Account {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final BigDecimal MINIMUM_BALANCE = BigDecimal.ZERO;

    public CurrentAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CURRENT;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
}