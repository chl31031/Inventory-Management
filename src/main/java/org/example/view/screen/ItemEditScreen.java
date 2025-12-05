package org.example.view.screen;

import org.example.Main;
import org.example.view.model.Item;
import org.example.view.viewcontroller.GetItem;
import org.example.view.viewcontroller.UpdateItem;

import java.util.Scanner;

public class ItemEditScreen extends Screen {

    private final String itemID;
    private Item item;
    private final GetItem getItem = Main.viewControllerContainer.getItem();
    private final UpdateItem updateItem = Main.viewControllerContainer.updateItem();

    public ItemEditScreen(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public void action() {
        this.item = getItem.execute(itemID);
        if (item == null) {
            Main.screens.removeLast();
            return;
        }
        var scanner = new Scanner(System.in);
        System.out.print(item.name() + "의 새로운 이름: ");
        var name = scanner.nextLine();
        System.out.println();
        updateItem.updateItem(item.id(), name);

        Main.screens.removeLast();
    }
}
