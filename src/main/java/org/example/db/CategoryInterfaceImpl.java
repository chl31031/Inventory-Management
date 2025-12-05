package org.example.db;

import org.example.util.DBConnection;
import org.example.util.exception.AlreadyExistException;
import org.example.util.exception.FkException;
import org.example.util.exception.NoIdException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryInterfaceImpl implements CategoryInterface {
    private static final CategoryInterfaceImpl INSTANCE = new CategoryInterfaceImpl();
    Connection conn = DBConnection.getConnection();

    private CategoryInterfaceImpl() {
    }

    @Override
    public List<String> getCategories() {
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM CATEGORY
                    """);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            }

            psmt.close();
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public void createCategory(String name) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    INSERT INTO CATEGORY VALUES(?)
                    """);

            psmt.setString(1, name);

            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            throw new AlreadyExistException();
        }

    }

    @Override
    public void deleteCategory(String id) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    DELETE FROM CATEGORY WHERE NAME = ?
                    """);
            psmt.setString(1, id);
            int i = psmt.executeUpdate();
            if (i < 1) throw new NoIdException();
        } catch (SQLException e) {
            throw new FkException();
        }
    }

    public static CategoryInterface getInstance() {
        return INSTANCE;
    }
}
