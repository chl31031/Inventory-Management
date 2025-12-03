package org.example.db;

import org.example.dto.CreateIODetail;
import org.example.dto.IODetail;

import java.util.List;

public interface IODetailInterface {
    void createIODetail(CreateIODetail createIODetail);
    void deleteIODetail(String id);
    List<IODetail> getIODetailByItemId(String id);
}
