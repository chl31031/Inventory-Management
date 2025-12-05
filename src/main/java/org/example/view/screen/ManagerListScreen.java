package org.example.view.screen;

import org.example.Main;
import org.example.view.model.Manager;
import org.example.view.viewcontroller.GetManagerList;

import java.util.List;
import java.util.Scanner;

public final class ManagerListScreen extends Screen {

    private final GetManagerList getManagerList = Main.viewControllerContainer.getManagerList();
    private List<Manager> managerList = null;

    private void printManagers() {
        var screenText = new StringBuilder();
        for (var i = 0; i < managerList.size(); i++) {
            var manager = managerList.get(i);
            screenText.append(i + 1).append(". ");
            screenText.append(manager.name()).append(' ');
            screenText.append(manager.grade());
            screenText.append('\n');
        }
        screenText.append("(A: 관리자 추가, 숫자: 관리자 선택)\n");
        System.out.print(screenText);
    }

    private void addManager() {
        System.out.println();
        Main.screens.add(new ManagerAddScreen());
    }

    private void selectManager(int index) {
        System.out.println();
        Main.screens.add(new ItemListScreen(managerList.get(index)));
    }

    @Override
    public void action() {
        managerList = getManagerList.execute();

        printManagers();

        var scanner = new Scanner(System.in);
        var selected = scanner.nextLine();
        if (selected.equalsIgnoreCase("a")) {
            addManager();
            return;
        }
        var selectedInt = Integer.parseInt(selected) - 1;
        if (selectedInt >= 0 && selectedInt < managerList.size()) {
            selectManager(selectedInt);
            return;
        }
    }
}
