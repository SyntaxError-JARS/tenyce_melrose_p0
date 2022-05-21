package com.revature.retoker.web.dto;

public class LoginCred {

        private String email;
        private String password;

        private String first_name;

        private String last_name;

        // JACKSON REQUIRES A NO ARG CONSTRUCTOR

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }
        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }
        }
