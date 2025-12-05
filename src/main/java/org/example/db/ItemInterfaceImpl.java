package org.example.db;

import org.example.dto.*;

import java.util.List;

public class ItemInterfaceImpl implements ItemInterface {
    @Override
    public void createItem(CreateItem createItem) {

    }

    @Override
    public List<Item> getItems(Integer page) {
        return List.of();
    }

    @Override
    public OutItemDetail getOutItemDetail(String id) {
        return null;
    }

    @Override
    public void updateItem(UpdateItem updateItem) {
    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public void changeItemQuantity(UpdateItemQuantity updateItemQuantity) {

    }

    @Override
    public Item getItemById(String id) {
        return null;
    }

    @Override
    public List<Item> searchItems(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchFilteredItems(FilteredKeyword filteredKeyword) {
        return List.of();
    }
}
