package org.example.db;

import org.example.dto.CreateItem;
import org.example.dto.Item;
import org.example.dto.OutItemDetail;

import java.util.List;

public class ItemInterfaceImpl implements ItemInterface {
    @Override
    public void createItem(CreateItem createItem) {

    }

    @Override
    public List<Item> getItems() {
        return List.of();
    }

    @Override
    public OutItemDetail getOutItemDetail(String id) {
        return null;
    }

    @Override
    public void updateItem(CreateItem createItem) {

    }

    @Override
    public void deleteItem(String id) {

    }
}
