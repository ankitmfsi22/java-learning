package banking.model;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    public CurrentAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CURRENT;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return BigDecimal.ZERO;
    }
}
