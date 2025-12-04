package org.example.view.viewcontroller.fake;

import org.example.view.model.Manager;
import org.example.view.viewcontroller.GetManagerList;
import org.example.view.viewcontroller.ViewControllerContainer;

import java.util.ArrayList;

public class FakeViewControllerContainer implements ViewControllerContainer {

    private final ArrayList<Manager> managers = new ArrayList<>();

    public FakeViewControllerContainer() {
        managers.add(new Manager("id", "재우", "직급1"));
    }

    @Override
    public GetManagerList getManagerList() {
        return () -> managers;
    }
}
