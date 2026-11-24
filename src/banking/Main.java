package banking;

import banking.exception.BankException;
import banking.model.Account;
import banking.model.AccountType;
import banking.model.Customer;
import banking.model.Transaction;
import banking.service.BankService;
import banking.service.BankServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BankService bankService = new BankServiceImpl();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = readNumber("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> createCustomer();
                    case 2 -> createAccount();
                    case 3 -> depositMoney();
                    case 4 -> withdrawMoney();
                    case 5 -> transferMoney();
                    case 6 -> checkBalance();
                    case 7 -> viewTransactionHistory();
                    case 8 -> viewAllCustomers();
                    case 9 -> {
                        System.out.println("Thank you for using Smart Banking System!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please enter 1 to 9.");
                }
            } catch (BankException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println("========== SMART BANKING SYSTEM ==========");
        System.out.println("1. Create Customer");
        System.out.println("2. Create Account");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. Check Balance");
        System.out.println("7. View Transaction History");
        System.out.println("8. View All Customers");
        System.out.println("9. Exit");
    }

    private static void createCustomer() {
        String name = readText("Enter Name: ");
        String email = readText("Enter Email: ");
        String phone = readText("Enter Phone: ");

        Customer customer = bankService.createCustomer(name, email, phone);
        System.out.println("Customer Created Successfully!");
        System.out.println("Customer ID: " + customer.getCustomerId());
    }

    private static void createAccount() {
        String customerId = readText("Enter Customer ID: ");
        AccountType accountType = readAccountType();

        Account account = bankService.createAccount(customerId, accountType);
        System.out.println("Account Created Successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getCustomer().getName());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Minimum Balance: " + formatAmount(account.getMinimumBalance()));
    }

    private static AccountType readAccountType() {
        while (true) {
            int option = readNumber("Select Account Type (1. Savings  2. Current): ");
            if (option == 1) {
                return AccountType.SAVINGS;
            }
            if (option == 2) {
                return AccountType.CURRENT;
            }
            System.out.println("Please enter 1 or 2.");
        }
    }

    private static void depositMoney() {
        String accountNumber = readText("Enter Account Number: ");
        BigDecimal amount = readAmount("Enter Amount: ");

        Transaction transaction = bankService.deposit(accountNumber, amount);
        System.out.println("Deposit Successful!");
        printTransaction(transaction);
    }

    private static void withdrawMoney() {
        String accountNumber = readText("Enter Account Number: ");
        BigDecimal amount = readAmount("Enter Amount: ");

        Transaction transaction = bankService.withdraw(accountNumber, amount);
        System.out.println("Withdraw Successful!");
        printTransaction(transaction);
    }

    private static void transferMoney() {
        String fromAccount = readText("Enter Source Account: ");
        String toAccount = readText("Enter Destination Account: ");
        BigDecimal amount = readAmount("Enter Amount: ");

        Transaction transaction = bankService.transfer(fromAccount, toAccount, amount);
        System.out.println("Transfer Successful!");
        printTransaction(transaction);
    }

    private static void checkBalance() {
        String accountNumber = readText("Enter Account Number: ");

        Account account = bankService.getAccount(accountNumber);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getCustomer().getName());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Balance: " + formatAmount(account.getBalance()));
    }

    private static void viewTransactionHistory() {
        String accountNumber = readText("Enter Account Number: ");

        List<Transaction> history = bankService.getTransactionHistory(accountNumber);
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : history) {
            System.out.println(transaction.getTransactionId()
                    + " | " + transaction.getType()
                    + " | From: " + showValue(transaction.getFromAccount())
                    + " | To: " + showValue(transaction.getToAccount())
                    + " | " + formatAmount(transaction.getAmount())
                    + " | " + transaction.getStatus());
        }
    }

    private static void viewAllCustomers() {
        List<Customer> customers = bankService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId()
                    + " | " + customer.getName()
                    + " | " + customer.getEmail()
                    + " | " + customer.getPhone());
        }
    }

    private static void printTransaction(Transaction transaction) {
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        if (transaction.getFromAccount() != null) {
            System.out.println("From Account: " + transaction.getFromAccount());
        }
        if (transaction.getToAccount() != null) {
            System.out.println("To Account: " + transaction.getToAccount());
        }
        System.out.println("Amount: " + formatAmount(transaction.getAmount()));
        System.out.println("Status: " + transaction.getStatus());
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readNumber(String message) {
        while (true) {
            try {
                return Integer.parseInt(readText(message));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static BigDecimal readAmount(String message) {
        while (true) {
            try {
                return new BigDecimal(readText(message));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    private static String formatAmount(BigDecimal amount) {
        return String.format("INR %.2f", amount);
    }

    private static String showValue(String value) {
        return value == null ? "-" : value;
    }
}
