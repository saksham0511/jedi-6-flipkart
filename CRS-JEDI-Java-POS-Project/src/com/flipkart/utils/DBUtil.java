package com.flipkart.utils;

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

    static {
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DB_URL = prop.getProperty("url");
        USER = prop.getProperty("user");
        PASS = prop.getProperty("password");
    }

    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}