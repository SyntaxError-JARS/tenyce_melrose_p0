package com.reavature.retoker.MODELS;

public class Customer {

    // Encapsulation: variables declared private retrievable via "get and set".--TM
    private String email;
    private String firstName;
    private String lastName;
    private String password;


    public Customer(String email, String firstName, String lastName, String password) {
        super();
        this.email= email;
        this.firstName= firstName;
        this.lastName= lastName;
        this.password= password;

    }

    public Customer(){

    }

    public String getEmail() { return email; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toFileString()  {
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(email).append(",")
                .append(firstName).append(",")
                .append(lastName).append(",")
                .append(password).append(",");

        return mutableString.toString();
    }



    public String toString() {
        return "Customer{" +
                ", email='" + email + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}