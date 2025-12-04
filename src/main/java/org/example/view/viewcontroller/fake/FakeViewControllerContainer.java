package org.example.view.viewcontroller.fake;

import org.example.view.model.Manager;
import org.example.view.viewcontroller.CreateManager;
import org.example.view.viewcontroller.GetManagerList;
import org.example.view.viewcontroller.ViewControllerContainer;

import java.util.ArrayList;
import java.util.Random;

public class FakeViewControllerContainer implements ViewControllerContainer {

    private final ArrayList<Manager> managers = new ArrayList<>();

    public FakeViewControllerContainer() {
        managers.add(new Manager("id", "재우", "직급1"));
    }

    @Override
    public GetManagerList getManagerList() {
        return () -> managers;
    }

    @Override
    public CreateManager createManager() {
        return (name, grade) -> {
            managers.addFirst(new Manager(
                    getRandomString(6), name, grade
            ));
        };
    }

    private String getRandomString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        var random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
