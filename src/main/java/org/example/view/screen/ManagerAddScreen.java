package org.example.view.screen;

import org.example.Main;
import org.example.view.viewcontroller.CreateManager;

import java.util.Scanner;

public class ManagerAddScreen extends Screen {

    private final CreateManager createManager = Main.viewControllerContainer.createManager();

    @Override
    public void action() {
        var scanner = new Scanner(System.in);
        System.out.print("관리자 이름: ");
        var name = scanner.nextLine();
        System.out.print("직급: ");
        var grade = scanner.nextLine();
        createManager.execute(name, grade);
        Main.screens.removeLast();
        System.out.println();
    }
}
