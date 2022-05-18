package com.reavature.retoker.DAOS;

import java.sql.*;

    public class AccountDao implements Crudable<Account> {

        try(
        Connection conn = ConnectionFactory.getInstance().getConnection();)

        {

            @Override
            public Account create (Account newAccount){
            return null;
        }
            @Override
            public Account[] findAll () {

            String sql = "select * from users";
            Statement s = conn.createStatement();


            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();

                account.setAccount_Number(rs.getInt("account_number"));
                account.setAccount_Type(rs.getString("account_type"));
                account.setBalance(rs.getInt("balance"));
                account.setEmail(rs.getString("email"));

                //What's this?
                accounts[index] = account;
                index++;
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
            System.out.println("...");
            return accounts;
        }

        @Override
        public Account findById(String id) {
            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "select * from account where account_number = ?";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(id));
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);

                if (rs.next() != false) {

                    Account account = new Account();

                    account.setAccount_Number(rs.getInt("account_number"));
                    account.setAccount_Type(rs.getString("account_type"));
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
        public boolean update(Account updatedObj) {
            return false;
        }

        @Override
        public boolean delete(String id) {
            return false;
        }

        public void deposit(String amount, String id) throws RuntimeException {
            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "Update balance=balance+? where account_Number=?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(amount));
                ps.setInt(2, Integer.parseInt(account_number));
                int rs = ps.executeUpdate(); /

                System.out.println("Deposit Complete");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            public void withdraw (String amount, String id){
                try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                    String sql = "Update balance=balance+? where account_Number=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(amount));
                    ps.setInt(2, Integer.parseInt(account_number));
                    int rs = ps.executeUpdate();

                    System.out.println(amount + "Withdraw Complete");


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}