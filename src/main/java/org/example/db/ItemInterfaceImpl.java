package org.example.db;

import org.example.dto.*;
import org.example.util.DBConnection;
import org.example.util.exception.NoChangedException;
import org.example.util.exception.UnknownException;
import org.example.util.exception.WrongCategoryException;
import org.example.util.exception.WrongIdException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemInterfaceImpl implements ItemInterface {
    private static final ItemInterfaceImpl INSTANCE = new ItemInterfaceImpl();
    Connection conn = DBConnection.getConnection();

    private ItemInterfaceImpl() {
    }

    public static ItemInterfaceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void createItem(CreateItem createItem) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    INSERT INTO ITEM VALUES(?,?,?,?)
                    """);

            psmt.setString(1, String.valueOf(UUID.randomUUID()));
            psmt.setString(2, createItem.name());
            psmt.setString(3, createItem.category());
            psmt.setInt(4, 0);

            psmt.executeUpdate();

            psmt.close();
        } catch (SQLException e) {
            int eCode = e.getErrorCode();
            if (eCode == 1452) throw new WrongCategoryException();
            else throw new UnknownException();
        }
    }

    /**
     * 제로베이스 넘버링 해주세요...
     */
    @Override
    public List<Item> getItems(Integer page) {
        ArrayList<Item> list = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM
                    LIMIT ?, ?
                    """);
            psmt.setInt(1, page * 10);
            psmt.setInt(2, 10);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                list.add(new Item(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

            psmt.close();
        } catch (SQLException e) {
            throw new UnknownException();
        }

        return list;
    }

    @Override
    public OutItemDetail getOutItemDetail(String id) {
        IODetailInterface ii = IODetailInterfaceImpl.getInstance();

        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM WHERE ID = ?
                    """);
            psmt.setString(1, id);
            ResultSet rs = psmt.executeQuery();

            if (!rs.next()) throw new WrongIdException();

            String itemId = rs.getString(1);
            String name = rs.getString(2);
            String categoryId = rs.getString(3);
            Integer quantity = rs.getInt(4);
            var item = new Item(id, name, categoryId, quantity);
            List<IODetail> details = ii.getIODetailByItemId(itemId);

            psmt.close();
            return new OutItemDetail(item, details);
        } catch (SQLException e) {
            throw new UnknownException();
        }
    }

    @Override
    public void updateItem(UpdateItem updateItem) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    UPDATE ITEM SET NAME = ?
                    WHERE ID = ?
                    """);

            psmt.setString(1, updateItem.name());
            psmt.setString(2, updateItem.id());

            int i = psmt.executeUpdate();
            psmt.close();
            if (i < 1) throw new NoChangedException();
            psmt.close();
        } catch (SQLException e) {
            throw new UnknownException();
        }
    }

    @Override
    public void deleteItem(String id) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    DELETE FROM ITEM WHERE ID=?
                    """);
            psmt.setString(1, id);

            int i = psmt.executeUpdate();
            psmt.close();

            if (i < 1) throw new NoChangedException();
        } catch (SQLException e) {
            throw new UnknownException();
        }
    }

    @Override
    public void changeItemQuantity(UpdateItemQuantity updateItemQuantity) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    UPDATE ITEM SET QUANTITY = ?
                    WHERE ID = ?
                    """);
            psmt.setInt(1, updateItemQuantity.quantity());
            psmt.setString(2, updateItemQuantity.id());

            int i = psmt.executeUpdate();

            psmt.close();

            if (i < 1) throw new NoChangedException();
        } catch (SQLException e) {
            throw new UnknownException();
        }
    }

    @Override
    public Item getItemById(String id) {
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM WHERE ID = ?
                    """);
            psmt.setString(1, id);

            ResultSet rs = psmt.executeQuery();
            if (!rs.next()) throw new WrongIdException();

            Item item = new Item(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            );

            psmt.close();
            return item;
        } catch (SQLException e) {
            throw new UnknownException();
        }
    }

    @Override
    public List<Item> searchItems(String keyword) {
        ArrayList<Item> al = new ArrayList<>();
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM
                    WHERE NAME = *?*
                    """);
            psmt.setString(1, keyword);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                al.add(new Item(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

            psmt.close();
        } catch (SQLException e) {
            throw new UnknownException();
        }
        return al;
    }

    @Override
    public List<Item> searchFilteredItems(FilteredKeyword filteredKeyword) {
        ArrayList<Item> al = new ArrayList<>();
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM
                    WHERE CATEGORY_ID = ? AND NAME = *?*
                    """);
            psmt.setString(1, filteredKeyword.category());
            psmt.setString(2, filteredKeyword.keyword());
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                al.add(new Item(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

            psmt.close();
        } catch (SQLException e) {
            throw new UnknownException();
        }
        return al;
    }

    @Override
    public List<Item> filteredItems(String category) {
        ArrayList<Item> al = new ArrayList<>();
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM
                    WHERE CATEGORY_ID = ?
                    """);
            psmt.setString(1, category);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                al.add(new Item(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

            psmt.close();
        } catch (SQLException e) {
            throw new UnknownException();
        }
        return al;
    }

    @Override
    public List<Item> filteredItems(String category) {
        ArrayList<Item> al = new ArrayList<>();
        try {
            PreparedStatement psmt = conn.prepareStatement("""
                    SELECT * FROM ITEM
                    WHERE CATEGORY_ID = ?
                    """);
            psmt.setString(1, category);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                al.add(new Item(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

            psmt.close();
        } catch (SQLException e) {
        }
        return al;
    }
}
