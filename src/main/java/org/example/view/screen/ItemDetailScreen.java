package org.example.view.screen;

import org.example.Main;
import org.example.view.model.Item;
import org.example.view.model.ItemIO;
import org.example.view.model.Manager;
import org.example.view.viewcontroller.GetItem;
import org.example.view.viewcontroller.GetItemIOList;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Scanner;

public class ItemDetailScreen extends Screen {

    private final String itemID;
    private final Manager manager;
    private Item item;
    private List<ItemIO> ioList = null;
    private final GetItem getItem = Main.viewControllerContainer.getItem();
    private final GetItemIOList getItemIOList = Main.viewControllerContainer.getItemIOList();
    private int page;

    public ItemDetailScreen(String itemID, Manager manager, int page) {
        this.itemID = itemID;
        this.manager = manager;
        if (page < 0) {
            page = 0;
        }
        this.page = page;
    }

    public ItemDetailScreen(String itemID, Manager manager) {
        this(itemID, manager, 0);
    }

    private void printItem() {
        this.ioList = getItemIOList.execute(item.id(), page);
        if (ioList == null) {
            return;
        }

        var p = new StringBuilder();
        p.append("아이템 이름: ").append(item.name()).append('\n');
        for (var i = 0; i < ioList.size(); i++) {
            var io = ioList.get(i);
            p.append(i + 1).append(". ");
            p.append(io.io()).append(" ");
            p.append(io.quantity()).append(" ");
            p.append(io.managerName()).append(" ");
            p.append(io.time().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))).append(" ");
            p.append('\n');
        }
        p.append("페이지: ").append(page + 1).append('\n');
        p.append("(e: 뒤로가기, p: 이전 페이지, n: 다음 페이지, 숫자: 해당 IO 제거)").append('\n');
        System.out.print(p);
    }

    private void nextPage() {
        Main.screens.removeLast();
        Main.screens.add(new ItemDetailScreen(itemID, manager, page + 1));
    }

    private void prevPage() {
        Main.screens.removeLast();
        Main.screens.add(new ItemDetailScreen(itemID, manager, page - 1));
    }

    private void exit() {
        Main.screens.removeLast();
    }

    private void addIODetail() {
        Main.screens.add(new ItemIOAddScreen(itemID, manager));
    }

    @Override
    public void action() {
        this.item = getItem.execute(itemID);
        if (item == null) {
            Main.screens.removeLast();
            return;
        }

        printItem();
        if (ioList == null) {
            return;
        }

        var scanner = new Scanner(System.in);
        var selected = scanner.nextLine();
        if (selected.equalsIgnoreCase("n")) {
            nextPage();
        } else if (selected.equalsIgnoreCase("p")) {
            prevPage();
        } else if (selected.equalsIgnoreCase("e")) {
            exit();
        } else if (selected.equalsIgnoreCase("a")) {
            addIODetail();
        } else if (selected.equalsIgnoreCase("x")) {

        } else if (selected.equalsIgnoreCase("r")) {

        }

        var selectedInt = Integer.parseInt(selected) - 1;
        if (selectedInt >= 0 && selectedInt < this.ioList.size()) {
            Main.screens.add(new ItemIORemoveScreen(ioList.get(selectedInt).id()));
        }

    }
}
