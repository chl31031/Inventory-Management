package org.example.db;

import org.example.dto.CreateManager;
import org.example.dto.Manager;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManagerInterfaceImpl implements ManagerInterface{
    @Override
    public List<Manager> getManagers() {
        return List.of();
    }

    @Override
    public void createManager(CreateManager createManager) {

        //1. 사용자 - UUID
        String generatedId = UUID.randomUUID().toString();

        // 2. SQL (id, name, grade)
        String sql = "INSERT INTO MANAGER (id, name, grade) " +
                "VALUES (?, ?, ?)";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, generatedId);
            pstmt.setString(2, createManager.name());
            pstmt.setString(3, createManager.grade());
//            4. 실행 (DB로 전송)
            int result = pstmt.executeUpdate();
            if(result > 0){
                System.out.println("Manager 등록 완료! ManagerID : " + generatedId);
            }



        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
