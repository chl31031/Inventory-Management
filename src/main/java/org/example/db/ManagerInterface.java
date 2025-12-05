package org.example.db;

import org.example.dto.CreateManager;
import org.example.dto.Manager;

import java.util.List;

public interface ManagerInterface {
    List<Manager> getManagers();
    void createManager(CreateManager createManager);

}
