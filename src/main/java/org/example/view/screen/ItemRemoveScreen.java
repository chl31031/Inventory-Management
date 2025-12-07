package org.example.view.screen;

import org.example.Main;
import org.example.view.viewcontroller.DeleteItem;
import org.example.view.viewcontroller.GetItem;

import java.util.Scanner;

public class ItemRemoveScreen extends Screen {

    private final String itemID;
    private final DeleteItem deleteItem = Main.viewControllerContainer.deleteItem();
    private final GetItem getItem = Main.viewControllerContainer.getItem();

    public ItemRemoveScreen(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public void action() {
        var item = getItem.execute(itemID);
        if (item == null) {
            return;
        }

        var scanner = new Scanner(System.in);
        System.out.println(item.name() + "을 삭제? (Y/N)");
        var line = scanner.nextLine();
        if (line.equalsIgnoreCase("Y")) {
            deleteItem.deleteItem(itemID);
            Main.screens.removeLast();
        }
        if (line.equalsIgnoreCase("N")) {
            Main.screens.removeLast();
        }

    }
}
