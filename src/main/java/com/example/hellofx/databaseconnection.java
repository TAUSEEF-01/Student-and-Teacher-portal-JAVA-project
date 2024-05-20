package com.example.hellofx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class databaseconnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "project";
        String databaseUser = "root";
        String databasePassword = "baby1_2panda";
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
