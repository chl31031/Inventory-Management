package org.example.view.screen;

import org.example.Main;
import org.example.view.viewcontroller.CreateItem;

import java.util.Scanner;

public class ItemAddScreen extends Screen {

    private String itemName = null;
    private final CreateItem createItem = Main.viewControllerContainer.createItem();

    @Override
    public void action() {
        var scanner = new Scanner(System.in);

        if (itemName == null || itemName.isBlank()) {
            System.out.print("이름: ");
            itemName = scanner.nextLine();
            return;
        }

        if (!hasResult(ResultKey.CATEGORY_ID) || getResult(ResultKey.CATEGORY_ID) == null) {
            removeResult(ResultKey.CATEGORY_ID);
            System.out.println("카테고리 선택");
            Main.screens.add(new CategorySelectScreen());
            return;
        }

        if (itemName != null && !itemName.isBlank() && hasResult(ResultKey.CATEGORY_ID) && getResult(ResultKey.CATEGORY_ID) != null) {
            createItem.createItem(itemName, getResult(ResultKey.CATEGORY_ID));
            Main.screens.removeLast();
            return;
        }
    }
}
