package org.example.view.viewcontroller;

import org.example.view.model.Item;

import java.util.List;

public interface GetItemList {
    List<Item> execute(int page, String search, String categoryID);
}
