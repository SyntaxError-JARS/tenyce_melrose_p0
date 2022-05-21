package com.revature.retoker.daos;

import com.revature.retoker.models.Customer;
import com.revature.retoker.util.ConnectionFactory;
import com.revature.retoker.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CustomerDao implements Crudable<Customer> {

    private Logger logger = Logger.getLogger();

    @Override
    public Customer create(Customer newCustomer) {

        System.out.println("newCustomer entering the DAO" + newCustomer);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into customer (email, firstname, lastname, password) values (?, ?, ?, ?)";

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

    //@Override
    public List<Customer> findAll() throws IOException {

        List<Customer> customers = new LinkedList<>();

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


                System.out.println("customer data recorded");
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return customers;
    }


    //@Override
    public Customer findById(String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from customer where account_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            Customer customer = new Customer();

            customer.setEmail(rs.getString("email"));
            customer.setFirstName(rs.getString("firstname")); // this column lable MUST MATCH THE DB
            customer.setLastName(rs.getString("lastname"));
            customer.setPassword(rs.getString("password"));


            return customer;
            //  } else{
            //     System.out.println("...");
            //  }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Customer updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public boolean checkEmail(String email) {

        logger.info("CustomerDao::checkEmail() : find a customer by email");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select email from Customer where email=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email); // remember DQL, select is only keywords for Query
            System.out.println("CustomerDao::checkEmail() : got a ResultSet");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Same Email is already exist!!!");
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



