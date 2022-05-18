package com.reavature.retoker.UTILITY;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    //What is this below? -------TM
    private Properties prop = new Properties();

    private ConnectionFactory() {
        try {
            prop.load(new FileReader("resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        static {

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
//Look up get Instance relation to get Connection. ----TM
        public static ConnectionFactory getInstance(){
            return connectionFactory;
        }

        //class Loader

        // Once we getInstance() we are able to execute getConnection to return a Connection to our database
        public statice Connection getConnection() {

            Connection conn = null;

            String mysqlJDBCDriver
                    ="url=jdbc:postgresql://localhost:5432/postgres?currentSchema=retoker";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "password";


            try {
                conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return conn;
}
