package com.revature.retoker.services;

import com.revature.retoker.daos.AccountDao;
import com.revature.retoker.daos.CustomerDao;
import com.revature.retoker.exceptions.ResourcePersistence;
import com.revature.retoker.models.Account;

import java.sql.SQLException;
import java.util.logging.Logger;


public class AccountServices {

    private final AccountDao AccountDao;
    private final Object CustomerDao;
    private CustomerDao customerDao;

    public AccountServices(AccountDao accountDao) {
        this.AccountDao = accountDao;
        this.CustomerDao = null;
    }

    //@Override
    public Account create(Account newObject) {
        return null;
    }

    //@Override
    public Account[] readAll() {return new Account[0];}

    //@Override
    public Account readById(String id) {
        Logger logger = null;
        Account account = new Account();

        try {
            Account Account = AccountDao.findById(id);
        } catch (ResourcePersistence e) {
            String s = "Data not Found";
        }
        return account;
    }


    public Account update(Account updatedObject) {
        return null;
    }

    // @Override
    public boolean delete(String account_number) {
        return false;
    }

    // @Override
    public boolean validateInput(Account newAccount) {
        if (newAccount == null) return false;
        if (newAccount.getAccountNumber() == 0) return false;
        if (newAccount.getAccountType() == null || newAccount.getAccountType().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");

    }

    public static void deposit(String value, String id) {
        AccountServices.deposit(value, id);
    }

    public static void withdraw(String value, String id) {

        String account_number;
        AccountServices.withdraw(value, id);
    }

    public boolean registerAccount(Account newAccount) {
        try {
            if (!validateAccountInput(newAccount)) {
                throw new RuntimeException();
            }

            validateAccountInput(newAccount);

            Account persistedAccount = AccountDao.create(newAccount);

            if (persistedAccount == null) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Complete " + newAccount);
        return true;

    }

    private boolean validateAccountInput(Account newAccount) {
        return false;
    }

    public Account readAccountByEmail(String id) {
        return null;
    }

    public Account withdraw(Account createUpdate) {
        return null;
    }
}
