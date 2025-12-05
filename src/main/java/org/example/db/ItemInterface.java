package org.example.db;

import org.example.dto.*;

import java.util.List;

public interface ItemInterface {
    void createItem(CreateItem createItem);
    List<Item> getItems(Integer page);
    OutItemDetail getOutItemDetail(String id);
    void updateItem(UpdateItem updateItem);
    void deleteItem(String id);
    void changeItemQuantity(UpdateItemQuantity updateItemQuantity);
    Item getItemById(String id);
    List<Item> searchItems(String keyword);
    List<Item> searchFilteredItems(FilteredKeyword filteredKeyword);

    Item findById(String id);
}
