package com.revature.retoker.services;

import com.revature.retoker.daos.CustomerDao;
import com.revature.retoker.exceptions.InvalidRequest;
import com.revature.retoker.models.Customer;
import com.revature.retoker.util.logging.Logger;

import java.io.IOException;
import java.util.List;



public class CustomerServices{

        private CustomerDao customerDao;
        private Logger logger = Logger.getLogger();

        public CustomerServices(CustomerDao customerDao) {
            super();
            this.customerDao = customerDao;
        }


        public CustomerServices() {

        }

        public boolean validateEmail(String email) {
           return customerDao.checkEmail(email);
        }

        public boolean validateEmailNotUsed(String email) {
            customerDao.checkEmail(email);
            return false;
        }

        public void getAccounts(String email) {

        }

        public boolean registerCustomer(Customer newCustomer) {
            System.out.println("User trying to be registered: " + newCustomer);
            if (!validateUserInput(newCustomer)) {
                System.out.println("Invalid");
                throw new InvalidRequest("Invalid");
            }

            // TODO: Will implement with JDBC (connecting to our database)
            validateEmailNotUsed(newCustomer.getEmail());

            Customer persistedUser = customerDao.create(newCustomer);

            if (persistedUser == null) {
                throw new RuntimeException("Incomplete");
            }
            System.out.println("Persisted" + newCustomer);
            return true;
        }

        private boolean validateUserInput(Customer newCustomer) {
            logger.debug("Validating " + newCustomer);
            if (newCustomer == null) return false;
            if (newCustomer.getEmail() == null || newCustomer.getEmail().trim().equals("")) return false;
            if (newCustomer.getFirstName() == null || newCustomer.getFirstName().trim().equals("")) return false;
            if (newCustomer.getLastName() == null || newCustomer.getLastName().trim().equals("")) return false;
            return newCustomer.getPassword() != null || !newCustomer.getPassword().trim().equals("");
        }


        public Customer create(Customer newCustomer) {

            logger.info("Registration Attempt" + newCustomer);
            if (!validateUserInput(newCustomer)) { // checking if false
                // TODO: throw - what's this keyword?
                throw new InvalidRequest("Invalid Input");}

            // TODO: Will implement with JDBC (connecting to our database)
            if (validateEmailNotUsed(newCustomer.getEmail())) {
                throw new InvalidRequest("Email Unavailable");
            }

               Customer persistedUser = customerDao.create(newCustomer);

            if (persistedUser == null) {
                throw new RuntimeException("User was not persisted to the database upon registration");
            }
            logger.info("Persisted" +newCustomer);
            return persistedUser;

        }

        //@Override
        public List<Customer> readAll() {
                logger.info("Reading");


                try {
                    //
                   List<Customer> customer = customerDao.findAll();
                    logger.info("Found Customer  \n");
//
                    return customer;

                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                    return null;
                }
            }


        public Customer update(Customer updatedObject) {
            return null;
        }


        public boolean delete(String email) {
            return false;
        }

        public boolean validateInput(Customer object) {
            return false;
        }


        public Customer readByEmail(String email){
                Customer Customer = customerDao.findById(email);
            return Customer;

        }

        public Customer authenticateCustomer(String email, String password) {
            return null;
        }

        public Customer readbyEmail(String email) {
            return null;
        }
    }
