package com.flipkart.utils;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    static Properties prop = new Properties();
    static InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("./config.properties");
    static String DB_URL;
    static String USER;
    static String PASS;



    public static Connection getConnection() {
        Connection connection = null;
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            //System.out.println("in dbutil exception 1");
            e.printStackTrace();
        }
        DB_URL = prop.getProperty("url");
        USER = prop.getProperty("user");
        PASS = prop.getProperty("password");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            //System.out.println("in dbutil exception");
            e.printStackTrace();
        }
        return connection;
    }

}*/


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DBUtil {

    private static Connection connection = null;

    public static Connection getConnection() {


        if (connection != null)
            return connection;
        else {
            try {
                /*System.out.println("In dbutils check 1");
                Properties prop = new Properties();
                System.out.println("In dbutils check 2");
                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("/config.properties");
                System.out.println("In dbutils check 3");
                prop.load(inputStream);
                System.out.println("In dbutils check 4");
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");*/
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRSProject", "root", "root");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }/* catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return connection;
        }
    }
}