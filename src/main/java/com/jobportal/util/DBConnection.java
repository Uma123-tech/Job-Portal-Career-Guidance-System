
package com.jobportal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/job_portal?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String DB_USER = "root";

    // Replace this example with your actual MySQL password
    private static final String DB_PASSWORD = "YOUR_MYSQL_PASSWORD";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                    "MySQL JDBC driver is missing: " + e.getMessage()
            );
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
