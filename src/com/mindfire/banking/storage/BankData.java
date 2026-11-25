package com.mindfire.banking.storage;

import com.mindfire.banking.model.Account;
import com.mindfire.banking.model.Customer;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Customer> customersById = new LinkedHashMap<>();
    private final Map<String, Customer> customersByEmail = new HashMap<>();
    private final Map<String, Account> accountsByNumber = new HashMap<>();

    private int customerCounter = 1001;
    private int accountCounter = 10001;
    private int transactionCounter = 1001;

    public Map<String, Customer> getCustomersById() {
        return customersById;
    }

    public Map<String, Customer> getCustomersByEmail() {
        return customersByEmail;
    }

    public Map<String, Account> getAccountsByNumber() {
        return accountsByNumber;
    }

    public String nextCustomerId() {
        return "CUST" + customerCounter++;
    }

    public String nextAccountNumber() {
        return String.valueOf(accountCounter++);
    }

    public String nextTransactionId() {
        return "TXN" + transactionCounter++;
    }
}
