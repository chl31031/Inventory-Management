package org.example;

import org.example.view.screen.ManagerListScreen;
import org.example.view.screen.Screen;
import org.example.view.viewcontroller.ViewControllerContainer;
import org.example.view.viewcontroller.db.DBViewControllerContainer;
import org.example.view.viewcontroller.fake.FakeViewControllerContainer;

import java.util.ArrayList;

public class Main {

    public static ViewControllerContainer viewControllerContainer = new DBViewControllerContainer();
    public static ArrayList<Screen> screens = new ArrayList<>();

    public static void main(String[] args) {
        screens.add(new ManagerListScreen());

        while (screens.size() > 0) {
            try {
                screens.getLast().action();
            } catch (Exception e) {
            }
        }
    }
}
