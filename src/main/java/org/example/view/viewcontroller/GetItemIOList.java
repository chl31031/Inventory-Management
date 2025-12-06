package org.example.view.viewcontroller;

import org.example.view.model.ItemIO;

import java.util.List;

public interface GetItemIOList {
    List<ItemIO> execute(String itemID, int page);
}
