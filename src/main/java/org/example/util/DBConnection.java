package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String url ="jdbc:mysql://localhost:3305/inventory_management";
    private static final String user = "root";
    private static final String pw = "1234";

    private static Connection conn;

    public static Connection getConnection() {
        if( conn != null ) { return conn; }

        try {
                conn = DriverManager.getConnection(url, user, pw);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
}
