package com.reavature.retoker.SERVICES;
import com.reavature.retoker.DAOS.*;
import com.reavature.retoker.EXCEPTIONS.*;



public class AccountServices {

    private CustomerDao customerDao;

    public AccountServices(AccountDao accountDao) {
        this.AccountDao = accountDao;
        this.CustomerDao = customerDao;
    }

    @Override
    public Account create(Account newObject) {
        return null;
    }

    @Override
    public Account[] readAll() {
        return AccountDao.findAll();
    }


    @Override
    public Account readById(String id) {
        Logger logger = null;
        Account account = new Account();

        try {
            account = AccountDao.findById(id);
        } catch (ResourcePersistance e) {
            logger.("Data not Found");
        }
        return account;
    }


    @Override
    public Account update(Account updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String account_number) {
        return false;
    }

    @Override
    public boolean validateInput(Account newAccount) {
        if (newAccount == null) return false;
        if (newAccount.getaccount_number() == 0) return false;
        if (newAccount.getAccount_type() == null || newAccount.getAccount_type().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");

    }

    public void deposit(String value, String id) {
        accountDao.deposit(value, account_number);
    }

    public void withdraw(String value, String id) {

        accountDao.withdraw(value, account_number);
    }

    public boolean registerAccount(Account newAccount) {
        if (!validateAccountInput(newAccount)) {
            throw new RuntimeException();
        }

        validateAccountInput(newAccount);

        Account persistedAccount = accountDao.create(newAccount);

        if (persistedAccount == null) {
            throw new RuntimeException();
        }
        System.out.println("Complete " + newAccount);
        return true;

    }

    private boolean validateAccountInput(account newAccount) {
        return false;
    }
}

