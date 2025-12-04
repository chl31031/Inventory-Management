package org.example.db;

import org.example.dto.Category;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryInterfaceImpl implements CategoryInterface {
    private static final CategoryInterfaceImpl INSTANCE = new CategoryInterfaceImpl();
    Connection conn = DBConnection.getConnection();

    private CategoryInterfaceImpl() {
    }

    @Override
    public List<Category> getCategories() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM CATEGORY
                    """);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                list.add(new Category(rs.getString(1), rs.getString(2)));
            }

        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public void createCategory(String name) {
        try {

            if (existByName(name)) return;

            PreparedStatement psmt = conn.prepareStatement("""
                    INSERT INTO CATEGORY VALUES(?, ?)
                    """);

            psmt.setString(1, String.valueOf(UUID.randomUUID()));
            psmt.setString(2, name);

            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
        }

    }

    @Override
    public void deleteCategory(String name) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    DELETE FROM CATEGORY WHERE NAME = ?
                    """);
            psmt.setString(1, name);
            psmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public boolean existByName(String name) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM CATEGORY WHERE ID = ?
                    """);

            psmt.setString(1, name);
            ResultSet rs = psmt.executeQuery();

            boolean exists = rs.next();

            rs.close();
            psmt.close();

            return exists;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String getCategoryIdByName(String name) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT 1 FROM CATEGORY WHERE NAME = ?
                    """);
            psmt.setString(1, name);
            ResultSet rs = psmt.executeQuery();
            String id = rs.getString(1);

            psmt.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static CategoryInterface getInstance() {
        return INSTANCE;
    }
}
