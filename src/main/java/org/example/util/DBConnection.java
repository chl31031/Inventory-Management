package org.example.util;

import java.sql.*;

public class DBConnection {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3305/inventory_management";
    private static final String user = "root";
    private static final String pw = "1234";

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn != null) {
            return conn;
        }

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 드라이버를 찾을 수 없습니다.");
            throw new SQLException("DB 드라이버 로드 실패", e);
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 연결 설정 중 오류 발생", e);
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException("PreparedStatement 닫기 중 오류 발생",e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Connection 닫기 중 오류 발생",e);
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException("ResultSet 닫기 중 오류 발생",e);
            }
        }
        close(conn, pstmt);
    }

}
