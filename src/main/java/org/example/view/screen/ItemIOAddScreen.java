package org.example.view.screen;

import org.example.Main;
import org.example.view.model.IO;
import org.example.view.viewcontroller.CreateItemIO;

import java.util.Scanner;

public class ItemIOAddScreen extends Screen {

    private final String itemID;
    private final CreateItemIO createItemIO = Main.viewControllerContainer.createItemIO();

    public ItemIOAddScreen(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public void action() {
        var scanner = new Scanner(System.in);
        System.out.println("IN / OUT: ");
        var ioString = scanner.nextLine();
        IO io = null;
        if ("IN".equalsIgnoreCase(ioString)) {
            io = IO.IN;
        } else if ("OUT".equalsIgnoreCase(ioString)) {
            io = IO.OUT;
        }
        if (io == null) {
            return;
        }
        System.out.println("개수: ");
        var quantity = scanner.nextInt();
        System.out.println();
        if (quantity < 0) {
            return;
        }
        createItemIO.createIO(itemID, io, quantity);
        Main.screens.removeLast();
    }
}
