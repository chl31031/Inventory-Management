package org.example.db;

import org.example.dto.CreateIODetail;
import org.example.dto.IODetail;
import org.example.dto.Item;
import org.example.dto.Manager;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IODetailInterfaceImpl implements IODetailInterface {
    private final ManagerInterface managerDao = ManagerInterfaceImpl.getInstance();
    private final ItemInterface itemDao = ItemInterfaceImpl.getInstance();

    private static final String INSERT_QUERY =
            "INSERT INTO IO_DETAIL (ID, IN_OUT, QUANTITY, IO_DATE, MANAGER_ID, ITEM_ID) " + "VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public String createIODetail(CreateIODetail dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //uuid 생성
        String newId = UUID.randomUUID().toString();

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(INSERT_QUERY);

            pstmt.setString(1, newId); // id
            pstmt.setString(2, dto.io().name());//
            pstmt.setInt(3, dto.quantity());
            pstmt.setDate(4, Date.valueOf(dto.date()));
            pstmt.setString(5, dto.managerId());
            pstmt.setString(6, dto.itemId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                //저장 후 생성된 아이디
                return newId;
            }
        } catch (SQLException e) {
            throw new RuntimeException("입출고 상세 정보 생성 중 오류 발생", e);
        } finally {
            DBConnection.close(conn, pstmt);
        }
        return null;
    }

    private static final String SELECT_BY_ITEM_ID_QUERY =
            "SELECT ID, IN_OUT, QUANTITY, IO_DATE, MANAGER_ID, ITEM_ID FROM IODetail WHERE ITEM_ID = ?";

    @Override
    public List<IODetail> getIODetailByItemId(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        java.sql.ResultSet rs = null;
        List<IODetail> ioDetails = new ArrayList<>(); //리스트 초기화

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SELECT_BY_ITEM_ID_QUERY);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String managerId = rs.getString("MANAGER_ID");
                String itemId = rs.getString("ITEM_ID");

                /*가짜 객체 반환 가정*/
                Manager manager = managerDao.findById(managerId);
                Item item = itemDao.findById(itemId);

                if (manager != null && item != null) {
                    IODetail detail = new IODetail(
                            rs.getString("ID"),
                            rs.getString("IN_OUT"),
                            rs.getInt("QUANTITY"),
                            manager,
                            item
                    );
                    ioDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("입출고 상세 정보 생성 중 오류 발생", e);
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return ioDetails;
    }

    @Override
    public boolean deleteIODetail(String id) {
        return false;
    }

}

