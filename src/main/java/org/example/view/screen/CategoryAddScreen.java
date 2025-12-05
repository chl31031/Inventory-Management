package org.example.view.screen;

import org.example.Main;
import org.example.view.viewcontroller.CreateCategory;

import java.util.Scanner;

public class CategoryAddScreen extends Screen {

    private final CreateCategory createCategory = Main.viewControllerContainer.createCategory();

    @Override
    public void action() {
        var scanner = new Scanner(System.in);
        System.out.print("카테고리 이름: ");
        var name = scanner.nextLine();
        if (name.isBlank()) {
            return;
        }
        createCategory.execute(name);
        Main.screens.removeLast();
    }
}
