package com.revature.retoker.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.retoker.daos.AccountDao;
import com.revature.retoker.daos.CustomerDao;
import com.revature.retoker.services.AccountServices;
import com.revature.retoker.services.CustomerServices;
import com.revature.retoker.web.servlets.AccountServlet;
import com.revature.retoker.web.servlets.AuthServlet;
import com.revature.retoker.web.servlets.CustomerServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

    @WebListener
    public class ContextLoaderListener implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            // Make our single ObjectMapper instance
            ObjectMapper mapper = new ObjectMapper();

            // Instantiate all Daos first
            CustomerDao customerDao = new CustomerDao();
            AccountDao accountDao = new AccountDao();

            // Instantiate and initialize the services with their dao dependency
            AccountServices accountServices = new AccountServices(accountDao);
            CustomerServices customerServices = new CustomerServices(customerDao);


            AuthServlet authServlet = new AuthServlet(customerServices, mapper);
            CustomerServlet customerServlet = new CustomerServlet(customerServices, mapper);
            AccountServlet accountServlet = new AccountServlet(accountServices, mapper);

            ServletContext context = sce.getServletContext();
            context.addServlet("AuthServlet", authServlet).addMapping("/auth");
            context.addServlet("CustomerServlet", customerServlet).addMapping("/customer/*");
            context.addServlet("AccountServlet", accountServlet).addMapping("/account/*");

        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            ServletContextListener.super.contextDestroyed(sce);
        }
    }
