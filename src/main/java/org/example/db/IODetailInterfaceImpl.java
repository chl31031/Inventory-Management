package org.example.db;

import org.example.dto.CreateIODetail;
import org.example.dto.IODetail;
import org.example.dto.Item;
import org.example.dto.Manager;
import org.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IODetailInterfaceImpl implements IODetailInterface {
    private Connection conn = DBConnection.getConnection();
    private static final IODetailInterface INSTANCE = new IODetailInterfaceImpl();

    private final ManagerInterface managerDao = ManagerInterfaceImpl.getInstance();
    private final ItemInterface itemDao = ItemInterfaceImpl.getInstance();

    private static final String INSERT_QUERY = """
            INSERT INTO IO_DETAIL (ID, IN_OUT, QUANTITY, IO_DATE, MANAGER_ID, ITEM_ID) 
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    @Override
    public String createIODetail(CreateIODetail dto) {
        String newId = UUID.randomUUID().toString();
        PreparedStatement pstmt = null;
        try {
            if (conn != null) {

            }
            pstmt = conn.prepareStatement(INSERT_QUERY);

            pstmt.setString(1, newId); // id
            pstmt.setString(2, dto.io().name());//
            pstmt.setInt(3, dto.quantity());
            pstmt.setDate(4, Date.valueOf(dto.date()));
            pstmt.setString(5, dto.managerId());
            pstmt.setString(6, dto.itemId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return newId;
            }
        } catch (SQLException e) {

        }finally {
            DBConnection.closeResources(pstmt, null);
        }
        return null;
    }

    private static final String SELECT_BY_ITEM_ID_QUERY =
            "SELECT ID, IN_OUT, QUANTITY, IO_DATE, MANAGER_ID, ITEM_ID FROM IODetail WHERE ITEM_ID = ?";

    @Override
    public List<IODetail> getIODetailByItemId(String id) {
        List<IODetail> ioDetails = new ArrayList<>(); //리스트 초기화

        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ITEM_ID_QUERY)) {

            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String managerId = rs.getString("MANAGER_ID");
                    String itemId = rs.getString("ITEM_ID");

                    //
                    Manager manager = managerDao.getManagerById(managerId);
                    Item item = itemDao.getItemById(itemId);

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
            }
        } catch (SQLException e) {

        }
        return ioDetails;
    }

    @Override
    public boolean deleteIODetail(String id) {
        return false;
    }


    public static IODetailInterface getInstance(){
        return INSTANCE;
    }

    private IODetailInterfaceImpl() {
    }
}
