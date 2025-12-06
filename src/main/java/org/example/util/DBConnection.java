package org.example.util;

import org.example.util.exception.UnknownException;

import java.sql.*;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3305/inventory_management";
    private static String user = "root";
    private static String pw = "1234";
    //모든 DAO 공유
    private static Connection conn;

    //Connection 객체 반환
    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        try {
            conn = DriverManager.getConnection(url, user, pw);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
        public static void closeResources (PreparedStatement pstmt, ResultSet rs){
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {

                }
            }
        }
    }
