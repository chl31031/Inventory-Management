package org.example.db;

import org.example.dto.CreateManager;
import org.example.dto.Manager;

import java.util.List;

public class ManagerInterfaceImpl implements ManagerInterface{
  private static final ManagerInterfaceImpl instance = new ManagerInterfaceImpl();

    private ManagerInterfaceImpl() {

    }
    public static ManagerInterfaceImpl getInstance(){
        return instance;
    }

    @Override
    public List<Manager> getManagers() {
        return List.of();
    }
    @Override
    public void createManager(CreateManager createManager) {

    }

    @Override
    public Manager getManagerById(String id){
       /* createIODetail test용
        System.out.println("가짜 객체" + id);
        return new Manager(
                id,
                "name",
                "grade"
        );*/
        return null;
    }
}
