package com.revature.retoker.daos;

import com.revature.retoker.models.Account;
import com.revature.retoker.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class AccountDao implements Crudable<Account> {




            //@Override
            public List<Account> findAll() throws IOException{

                List<Account> accounts = new LinkedList<>();

//connection is auto closable
                try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

    String sql = "select * from users";
            Statement s = conn.createStatement();


            ResultSet rs = s.executeQuery(sql);

                    while (rs.next()) { // the last line of the file is null
                        Account account = new Account();

                account.setAccountNumber(rs.getInt("account_number"));
                account.setAccountType(rs.getString("account_type"));
                account.setBalance(rs.getInt("balance"));
                account.setEmail(rs.getString("email"));


            accounts.add(account);

            }
        } catch( SQLException e){
            e.printStackTrace();
            return null;
        }
            System.out.println("...");
            return accounts;
        }

    @Override
    public Account create(Account newObject){
        return null;
    }


    //@Override
    public Account findById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "select * from account where account_number = ?";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(id));
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);

                if (rs.next() != false) {

                    Account account = new Account();

                    account.setAccountNumber(rs.getInt("account_number"));
                    account.setAccountType(rs.getString("account_type"));
                    account.setBalance(rs.getInt("balance"));
                    account.setEmail(rs.getString("email"));

                    return account;
                } else {
                    System.out.println("No User Data");
                    return null;
                }

            } catch (SQLException e) {
                e.printStackTrace();

                return null;
            }
        }


        @Override
        public boolean update(Account updateObject) {
            return false;
        }

        @Override
        public boolean delete(String id) {
            return false;
        }

        public boolean deposit(String amount, String email) throws RuntimeException {
            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "Update account SET balance=? where email=?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(amount));
                ps.setString(2, email);
                int rs = ps.executeUpdate();
                int checkUpdate = ps.executeUpdate();
                if (checkUpdate == 0) {
                    throw new RuntimeException();
                } else return true;

            } catch (SQLException e) {
              e.printStackTrace();
                return false;
            }

//            public void withdraw (String amount, String id){
//                try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
//
//                    String sql = "Update balance=balance+? where account_Number=?";
//                    PreparedStatement ps = conn.prepareStatement(sql);
//                    ps.setInt(1, Integer.parseInt(amount));
//                    ps.setInt(2, Integer.parseInt(accountNumber));
//                    int rs = ps.executeUpdate();
//
//                    System.out.println(amount + "Withdraw Complete");
//
//
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
            }
        }
