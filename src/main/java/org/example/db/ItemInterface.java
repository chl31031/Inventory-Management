package org.example.db;

import org.example.dto.CreateItem;
import org.example.dto.Item;
import org.example.dto.OutItemDetail;

import java.util.List;

public interface ItemInterface {
    void createItem(CreateItem createItem);
    List<Item> getItems();
    OutItemDetail getOutItemDetail(String id);
    void updateItem(CreateItem createItem);
    void deleteItem(String id);
}
