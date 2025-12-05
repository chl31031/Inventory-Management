package org.example.db;

import org.example.dto.*;

import java.util.List;

public class ItemInterfaceImpl implements ItemInterface {
    private static final ItemInterfaceImpl instance = new ItemInterfaceImpl();

    private ItemInterfaceImpl(){

    }

    public static ItemInterfaceImpl getInstance(){
        return instance;
    }

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
    public List<Item> searchItems(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchFilteredItems(FilteredKeyword filteredKeyword) {
        return List.of();
    }

    @Override
    public Item getItemById(String id) {
/* createIODetail test용*/
        System.out.println("가짜" + id);
        return new Item(
                id,
                "name",
                "category",
                1000
        );
//        return null;
    }
}
