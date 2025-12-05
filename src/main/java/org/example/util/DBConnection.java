package org.example.util;

import java.sql.*;

public class DBConnection {
    //싱글톤 인스턴스
    private static final DBConnection instance = new DBConnection();

    //모든 DAO 공유
    private Connection conn;

   private DBConnection() {
       try {
           String url = "jdbc:mysql://localhost:3305/inventory_management";
           String user = "root";
           String pw = "1234";


           this.conn = DriverManager.getConnection(url, user, pw);

       }catch (SQLException e) {

       }
   }
   public static DBConnection getInstance() {
       return instance;
   }
   //Connection 객체 반환
   public Connection getConnection() {
       return this.conn;
   }
   public void closeConnection() {
       try {
           if (this.conn != null && !this.conn.isClosed()) {
               this.conn.close();
           }
       } catch (SQLException e) {

       }
   }
   public static void closeResources(PreparedStatement pstmt, ResultSet rs) {
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
