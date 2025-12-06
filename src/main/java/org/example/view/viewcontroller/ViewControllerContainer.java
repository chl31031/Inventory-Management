package org.example.view.viewcontroller;

public interface ViewControllerContainer {
    GetManagerList getManagerList();
    CreateManager createManager();
    GetItemList getItemList();
    CreateCategory createCategory();
    GetCategoryList getCategoryList();
    CreateItem createItem();
    GetItem getItem();
    GetItemIOList getItemIOList();
    CreateItemIO createItemIO();
    DeleteItemIO deleteItemIO();
}
