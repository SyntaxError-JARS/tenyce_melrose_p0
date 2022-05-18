package com.reavature.retoker.DAOS;

import java.io.IOException;
import java.sql.*;
import com.reavature.retoker.EXCEPTIONS.*;


public class CustomerDao {

    private class CustomerDao implements Crudable<Customer> {

        @Override
        public Customer create(Customer newCustomer) {
            System.out.println("newCustomer entering the DAO" + newCustomer);

            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
                // String sql = "insert into trainer value (" + newTrainer.getFname() + "," + newTrainer.getLname();

                // The commented out sql String is using default for auto-generating the ID if you used serial
                // String sql = "insert into trainer values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
                String sql = "insert into customer (email, firstname, lastname, password) values (?, ?, ?, ?, ?)";

                PreparedStatement ps = conn.prepareStatement(sql);

                System.out.println(newCustomer.getFirstName());
                System.out.println(newCustomer.getLastName());

                // 1-indexed, so first ? starts are 1
                ps.setString(1, newCustomer.getEmail());
                ps.setString(2, newCustomer.getFirstName());
                ps.setString(3, newCustomer.getLastName());
                ps.setString(4, newCustomer.getPassword());


                int checkInsert = ps.executeUpdate();

                if (checkInsert == 0) {
                    throw new RuntimeException();
                }

            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
                return null;
            }
            return newCustomer;
        }

        @Override
        public Customer[] findAll() throws IOException {

            // Customer class array
            Customer[] trainers = new Customer[10];

            int index = 0;

            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "select * from customer";
                Statement s = conn.createStatement();


                ResultSet rs = s.executeQuery(sql);

                while (rs.next()) {
                    Customer customer = new Customer();

                    customer.setEmail(rs.getString("email"));
                    customer.setFirstName(rs.getString("firstname")); // this column lable MUST MATCH THE DB
                    customer.setLastName(rs.getString("lastname"));
                    customer.setPassword(rs.getString("password"));


                    System.out.println("customer data recorded" + index);
                    trainers[index] = customer;
                    index++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            return new Customer[0];
        }


        @Override
        public Customer findById(String id) {

            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "select * from customer where account_number = ?";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

                ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

                Customer customer = new Customer();

                customer.setEmail(rs.getString("email"));
                customer.setFirstname(rs.getString("firstname")); // this column lable MUST MATCH THE DB
                customer.setLastname(rs.getString("lastname"));
                customer.setPassword(rs.getString("password"));


                return customer;
            } else{
                System.out.println("...");
            }

        } catch(
        SQLException e)

        {
            e.printStackTrace();
            return null;
        }

        @Override
        public boolean update(Customer updatedObj) {
            return false;
        }

        @Override
        public boolean delete(String id) {
            return false;
        }

        public void checkEmail(String email) {
            String sql = "select email from customer where email = " + email;
        }
    } catch(
    SQLException e);

    {
}

}
