package com.revature.retoker.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.retoker.exceptions.ResourcePersistence;
import com.revature.retoker.models.Customer;
import com.revature.retoker.services.CustomerServices;
import com.revature.retoker.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


    public class CustomerServlet extends HttpServlet implements Authable {

        private final CustomerServices customerServices;
        private final ObjectMapper mapper;
        private final Logger logger = Logger.getLogger();

        public CustomerServlet(CustomerServices customerServices, ObjectMapper mapper) {
            this.customerServices = customerServices;
            this.mapper = mapper;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            if (!Authable.checkAuth(req, resp)) return;



            // Handling the query params in the /users?id=x&email=y
            if (req.getParameter("email") != null && req.getParameter("email") != null) {
                resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("email") + " " + req.getParameter("email"));
                return;
            }

            // Handling the query params in the endpoint /users?id=x
            if (req.getParameter("email") != null) {
                Customer customer;
                try {
                    customer = customerServices.readByEmail(req.getParameter("email")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
                } catch (ResourcePersistence e) {
                    logger.warn(e.getMessage());
                    resp.setStatus(404);
                    resp.getWriter().write(e.getMessage());
                    return;
                }
                String payload = mapper.writeValueAsString(customer);
                resp.getWriter().write(payload);
                return;
            }

            List<Customer>customer = customerServices.readAll();
            String payload = mapper.writeValueAsString(customer);

            resp.getWriter().write(payload);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // TODO: Let's create a User
            Customer newUser = mapper.readValue(req.getInputStream(), Customer.class); // from JSON to Java Object (User)
            Customer persistedUser = customerServices.create(newUser);

            String payload = mapper.writeValueAsString(persistedUser); // Mapping from Java Object (User) to JSON

            resp.getWriter().write("Persisted the provided user as show below \n");
            resp.getWriter().write(payload);
            resp.setStatus(201);
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        }
    }

