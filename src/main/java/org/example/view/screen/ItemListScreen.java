package org.example.view.screen;

import org.example.Main;
import org.example.view.model.Item;
import org.example.view.model.Manager;
import org.example.view.viewcontroller.GetItemList;

import java.util.List;
import java.util.Scanner;

public final class ItemListScreen extends Screen {
    private final Manager manager;
    private final GetItemList getItemList = Main.viewControllerContainer.getItemList();
    private final int page;
    private String search;
    private String categoryFilterID;
    private List<Item> items = null;

    public ItemListScreen(Manager manager, int page, String search, String categoryFilterID) {
        this.manager = manager;
        if (page < 0) {
            page = 0;
        }
        this.page = page;
        this.search = search;
        this.categoryFilterID = categoryFilterID;
    }

    public ItemListScreen(Manager manager) {
        this(manager, 0, null, null);
    }

    private void printItems() {
        var screenText = new StringBuilder();
        screenText.append(manager.name()).append('\n');
        for (var i = 0; i < items.size(); i++) {
            var item = items.get(i);
            screenText.append(i + 1).append(". ");
            screenText.append(item.name()).append(" ");
            screenText.append(item.quantity()).append(" ");
            screenText.append(item.category()).append(" ");
            screenText.append('\n');
        }
        screenText.append("페이지: ").append(page + 1).append('\n');
        screenText.append("(e: 뒤로가기, p: 이전 페이지, n: 다음 페이지, c: 카테고리 추가, s: 아이템 이름 검색, 숫자: 아이템 선택)").append('\n');
        System.out.print(screenText);
    }

    private void nextPage() {
        System.out.println();
        Main.screens.removeLast();
        Main.screens.add(new ItemListScreen(manager, page + 1, search, categoryFilterID));
    }

    private void prevPage() {
        System.out.println();
        Main.screens.removeLast();
        Main.screens.add(new ItemListScreen(manager, page - 1, search, categoryFilterID));
    }

    private void exit() {
        System.out.println();
        Main.screens.removeLast();
    }

    private void addItem() {

    }

    private void addCategory() {
        System.out.println();
        Main.screens.add(new CategoryAddScreen());
    }

    private void searchItem() {
        System.out.println();
        Main.screens.add(new ItemSearchScreen(search));
    }

    private void filterCategory() {
        System.out.println();
        Main.screens.add(new CategorySelectScreen());
    }

    @Override
    public void action() {
        var searchResult = getResult(ResultKey.SEARCH);
        if (searchResult != null) {
            this.search = searchResult;
            removeResult(ResultKey.SEARCH);
        }
        if (hasResult(ResultKey.CATEGORY_ID)) {
            this.categoryFilterID = getResult(ResultKey.CATEGORY_ID);
            removeResult(ResultKey.CATEGORY_ID);
        }
        items = getItemList.execute(page, this.search, this.categoryFilterID);

        printItems();
        var scanner = new Scanner(System.in);
        var selected = scanner.nextLine();
        if (selected.equalsIgnoreCase("n")) {
            nextPage();
        } else if (selected.equalsIgnoreCase("p")) {
            prevPage();
        } else if (selected.equalsIgnoreCase("e")) {
            exit();
        } else if (selected.equalsIgnoreCase("a")) {
            addItem();
        } else if (selected.equalsIgnoreCase("c")) {
            addCategory();
        } else if (selected.equalsIgnoreCase("s")) {
            searchItem();
        } else if (selected.equalsIgnoreCase("f")) {
            filterCategory();
        }

        var selectedInt = Integer.parseInt(selected) - 1;
        var item = items.get(selectedInt);
    }
}
