package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseconnection_2 {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "project";
        String databaseUser = "root";
        String databasePassword = "password";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
