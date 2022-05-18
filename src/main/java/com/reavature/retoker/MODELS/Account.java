package com.reavature.retoker.MODELS;

public class Account {

    private int accountNumber;

    private String accountType;

    private double balance;

    private String email;


    public Account() {
        super();
    }

    public Account(int accountNumber, String accountType, Double balance, String email) {
        super();
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.email = email;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getbalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "CustomerAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType'" + accountType + '\'' +
                ", balance='" + balance + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
