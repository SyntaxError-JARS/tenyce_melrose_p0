package com.revature.retoker.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.retoker.models.Account;
import com.revature.retoker.services.AccountServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

    public class AccountServlet extends HttpServlet implements Authable {

        private final AccountServices accountServices;
        private final ObjectMapper mapper;

        public AccountServlet(AccountServices accountServices, ObjectMapper mapper) {
            this.accountServices = accountServices;
            this.mapper = mapper;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if(req.getParameter("email") != null){
                Account account = accountServices.readById(req.getParameter("email"));
                String payload = mapper.writeValueAsString(account);
                resp.getWriter().write(payload);
                return;
            }

            List<Account> accounts = Arrays.asList(accountServices.readAll());
            String payload = mapper.writeValueAsString(accounts);

            resp.getWriter().write(payload);

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            // TODO: Let's create a account
            Account newAccount = mapper.readValue(req.getInputStream(), Account.class); // from JSON to Java Object (Pokemon)
            resp.getWriter().write(newAccount.getEmail() + newAccount.getEmail());
            Account persistedAccount = accountServices.create(newAccount);

            String payload = mapper.writeValueAsString(persistedAccount); // Mapping from Java Object (Account) to JSON

            resp.getWriter().write("Persisted the provided account as show below \n");
            resp.getWriter().write(payload);
            resp.setStatus(201);
        }


//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       Account accountUpdate = mapper.readValue(req.getInputStream(), Account.class);
//        Account updatedAccount = accountServices.update(accountUpdate);
//
//       String payload = mapper.writeValueAsString(accountUpdate);
//        resp.getWriter().write(payload);
//
//    }
//
//}

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(!Authable.checkAuth(req, resp)) return;

            Account createUpdate = mapper.readValue(req.getInputStream(), Account.class);
            Account updatedAccount = accountServices.withdraw(createUpdate);

            String payload = mapper.writeValueAsString(updatedAccount);
            resp.getWriter().write("You have successfully withdraw into your account");
        }
}
