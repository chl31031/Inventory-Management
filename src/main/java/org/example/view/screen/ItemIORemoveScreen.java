package org.example.view.screen;

import org.example.Main;
import org.example.view.viewcontroller.DeleteItemIO;

import java.util.Scanner;

public class ItemIORemoveScreen extends Screen {

    private final String ioID;
    private final DeleteItemIO deleteIO = Main.viewControllerContainer.deleteItemIO();

    public ItemIORemoveScreen(String ioID) {
        this.ioID = ioID;
    }

    @Override
    public void action() {
        var scanner = new Scanner(System.in);
        System.out.println("삭제? (Y/N) ");
        var line = scanner.nextLine();
        System.out.println();
        if (line.equalsIgnoreCase("Y")) {
            deleteIO.execute(ioID);
            Main.screens.removeLast();
        }
        if (line.equalsIgnoreCase("N")) {
            Main.screens.removeLast();
        }
    }
}
