package com.reavature.retoker.SERVICES;

import java.io.IOException;
import com.reavature.retoker.SERVICES.*;
import com.reavature.retoker.DAOS.*;
import com.reavature.retoker.UTILITY.LOGGING.logger;


    public class CustomerServices implements Serviceable<Services> {

        private CustomerDao customerDao;
        private Logger logger = Logger.getLogger();

        public CustomerServices(CustomerDao usersDao) {
            this.customerDao = customerDao;
        }


        private CustomerDao userDao = new CustomerDao();

        public CustomerServices() {

        }

        public boolean validateEmail(String email) {
            return userDao.checkEmail(email);
        }

        public boolean validatePassword(String email, String password) {
            return userDao.checkPassword(email, password);
        }

        public boolean validateEmailNotUsed(String email) {
            userDao.checkEmail(email);
            return false;
        }

        public void getAccounts(String email) {

        }

        public boolean registerUser(Customer newCustomer) {
            System.out.println("User trying to be registered: " + newCustomer);
            if (!validateUserInput(newCustomer)) {
                System.out.println("Invalid");
                throw new InvalidRequest("Invalid");
            }

            // TODO: Will implement with JDBC (connecting to our database)
            validateEmailNotUsed(newCustomer.getEmail());

            Customer persistedUser = userDao.create(newCustomer);

            if (persistedUser == null) {
                throw new ResourcePersistance("Incomplete"");
            }
            System.out.println("Persisted" + newCustomer);
            return true;
        }

        private boolean validateUserInput(Customer newCustomer) {
            logger.debug("Validating " + newCustomer);
            if (newCustomer == null) return false;
            if (newCustomer.getFirstName() == null || newCustomer.getFirstName().trim().equals("")) return false;
            if (newCustomer.getLastName() == null || newCustomer.getLastName().trim().equals("")) return false;
            if (newCustomer.getEmail() == null || newCustomer.getEmail().trim().equals("")) return false;
            return newCustomer.getpassword() != null || !newCustomer.getpassword().trim().equals("");
        }

        @Override
        public newCustomer create(Customer newCustomer) {

            logger.info("Registration Attempt" + newCustomer);
            if (!validateInput(newCustomer)) { // checking if false
                // TODO: throw - what's this keyword?
                throw new InvalidRequest("Invalid Input")

            // TODO: Will implement with JDBC (connecting to our database)
            if (validateEmailNotUsed(newCustomer.getEmail())) {
                throw new InvalidRequest("Email Unavailable");
            }

               Customer persistedUser = customerDao.create(newCustomer);

            if (persistedUser == null) {
                throw new ResourcePersistance("User was not persisted to the database upon registration");
            }
            logger.info("Persisted" +newCustomer);
            return persistedUser;

        }

        @Override
        public Customer[] readAll() {
                logger.info("Reading");


                try {
                    //
                    Customer[] users = CustomerDao.findAll();
                    logger.info("Found Customer  \n");
//
                    return users;

                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                    return null;
                }
            }

        @Override
        public Customer update(Customer updatedObject) {
            return null;
        }

        @Override
        public boolean delete(String email password) {
            return false;
        }

        @Override
        public boolean validateInput(Customer object) {
            return false;
        }


        public Customer readByEmail(String email) throws ResourcePersistance{
                Customer Customer = CustomerDao.findByEmail(email);
            return Customer;

        }

        public Customer authenticateCustomer(String email, String password) {
            return null;
        }

        public Customer readbyEmail(String email) {
            return null;
        }
    }


}
