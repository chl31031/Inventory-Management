package org.example.view.screen;

import org.example.Main;
import org.example.view.model.Item;
import org.example.view.model.Manager;
import org.example.view.viewcontroller.GetItemList;

import java.util.List;
import java.util.Scanner;

public final class ItemListScreen implements Screen {
    private final Manager manager;
    private final GetItemList getItemList = Main.viewControllerContainer.getItemList();
    private final int page;
    private final String search;
    private final String categoryID;
    private List<Item> items = null;

    public ItemListScreen(Manager manager, int page, String search, String categoryID) {
        this.manager = manager;
        if (page < 0) {
            page = 0;
        }
        this.page = page;
        this.search = search;
        this.categoryID = categoryID;
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
        screenText.append("(e: 뒤로가기, p: 이전 페이지, n: 다음 페이지, 숫자: 아이템 선택)").append('\n');
        System.out.print(screenText);
    }

    private void nextPage() {
        System.out.println();
        Main.screens.removeLast();
        Main.screens.add(new ItemListScreen(manager, page + 1, search, categoryID));
    }

    private void prevPage() {
        System.out.println();
        Main.screens.removeLast();
        Main.screens.add(new ItemListScreen(manager, page - 1, search, categoryID));
    }

    private void exit() {
        System.out.println();
        Main.screens.removeLast();
    }

    private void addItem() {

    }

    @Override
    public void action() {
        items = getItemList.execute(page, null, null);

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
        }

        var selectedInt = Integer.parseInt(selected) - 1;
        var item = items.get(selectedInt);
    }
}
