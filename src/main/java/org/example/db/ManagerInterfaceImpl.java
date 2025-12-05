package org.example.db;

import org.example.dto.CreateManager;
import org.example.dto.Manager;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManagerInterfaceImpl implements ManagerInterface {
    private static ManagerInterfaceImpl instance;

    // 2. 생성자를 private로 막아서 외부에서 new 금지
    private ManagerInterfaceImpl() {

    }

    // 3. [싱글톤] 외부에서 이 객체를 가져갈 수 있는 유일한 통로
    public static ManagerInterfaceImpl getInstance() {
        if (instance == null) {
            instance = new ManagerInterfaceImpl();
        }
        return instance;
    }

    @Override
    public List<Manager> getManagers() {
        //1. 데이터 담을 빈 리스트 생성
        List<Manager> managerList = new ArrayList<>();
        // 2. 전체 매니저를 조회하는 쿼리 작성
        String sql = "SELECT * FROM MANAGER";

        try  {

            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 3. 쿼리 실행 결과를 ResultSet에 담음 ( 조회는 executeQuery 사용)
            ResultSet rs = pstmt.executeQuery();
            System.out.println("리스트 목록이 불러왔습니다.");

            while (rs.next()) {
                //DB 컬럼명  : ID, NAME, GRADE
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String grade = rs.getString("GRADE");
                // 5. 꺼낸 값으로 Manager 객체(DTO) 생성
                Manager manager = new Manager(id, name, grade);

                //6. 리스트에 추가
                managerList.add(manager);
                System.out.println(manager);


            }//end while
            System.out.println("===============================");
            pstmt.close();
        } catch (Exception e) {
        }

        return managerList;
    }

    @Override
    public void createManager(CreateManager createManager) {

        //1. 사용자 - UUID
        String generatedId = UUID.randomUUID().toString();

        // 2. SQL (id, name, grade) - 코드블록
        String sql = """
                 INSERT INTO MANAGER(id, name, grade)
                 VALUES(?, ?, ?)""";

        try  {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, generatedId);
            pstmt.setString(2, createManager.name());
            pstmt.setString(3, createManager.grade());
//            4. 실행 (DB로 전송)
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Manager 등록 완료! ManagerID : " + generatedId);
            }


        } catch (Exception e) {

        }


    }
}
