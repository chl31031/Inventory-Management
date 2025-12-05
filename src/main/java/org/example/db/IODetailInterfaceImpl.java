package org.example.db;

import org.example.dto.CreateIODetail;
import org.example.dto.IODetail;

import java.util.List;

public class IODetailInterfaceImpl implements IODetailInterface{
    @Override
    public void createIODetail(CreateIODetail createIODetail) {

    }

    @Override
    public void deleteIODetail(String id) {

    }

    @Override
    public List<IODetail> getIODetailByItemId(String id) {
        return List.of();
    }

    public static IODetailInterfaceImpl getInstance(){
        return null;
    }
}
