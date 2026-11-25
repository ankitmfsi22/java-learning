package com.mindfire.banking.model;

import com.mindfire.banking.enums.TransactionStatus;
import com.mindfire.banking.enums.TransactionType;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String transactionId;
    private final TransactionType type;
    private final String fromAccount;
    private final String toAccount;
    private final BigDecimal amount;
    private final TransactionStatus status;
    private final LocalDateTime dateTime;

    public Transaction(String transactionId, TransactionType type, String fromAccount,
                       String toAccount, BigDecimal amount, TransactionStatus status) {
        this.transactionId = transactionId;
        this.type = type;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = status;
        this.dateTime = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return transactionId
                + " | " + type
                + " | From: " + (fromAccount == null ? "-" : fromAccount)
                + " | To: " + (toAccount == null ? "-" : toAccount)
                + " | INR " + amount.setScale(2)
                + " | " + status;
    }
}
