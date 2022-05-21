package com.revature.retoker.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.retoker.exceptions.InvalidRequest;
import com.revature.retoker.models.Customer;
import com.revature.retoker.services.CustomerServices;
import com.revature.retoker.web.dto.LoginCred;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final CustomerServices customerServices;
    // ObjectMapper provided by jackson
    private final ObjectMapper mapper;

    public AuthServlet(CustomerServices customerServices, ObjectMapper mapper) {
        this.customerServices = customerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            // The jackson library has the ObjectMapper with methods to readValues from the HTTPRequest body as an input stream and assign it to the class

            LoginCred loginCred = mapper.readValue(req.getInputStream(), LoginCred.class);
            Customer authUser = customerServices.authenticateCustomer(loginCred.getEmail(), loginCred.getPassword());


            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);

            resp.getWriter().write("You have successfully logged in!");
        } catch ( InvalidRequest e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }
    }
}