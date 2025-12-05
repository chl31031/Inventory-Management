package org.example.db;

import org.example.dto.CreateManager;
import org.example.dto.Manager;

import java.util.List;

public class ManagerInterfaceImpl implements ManagerInterface{
    @Override
    public List<Manager> getManagers() {
        return List.of();
    }

    @Override
    public void createManager(CreateManager createManager) {

    }
}
