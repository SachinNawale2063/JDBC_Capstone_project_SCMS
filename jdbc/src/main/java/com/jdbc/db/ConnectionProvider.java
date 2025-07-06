package com.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static final String url = "jdbc:mysql://localhost:3306/student_course_management_system";
    private static final String username = "root";
    private static final String password = "Sachin@12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
