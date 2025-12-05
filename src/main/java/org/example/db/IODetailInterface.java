package org.example.db;

import org.example.dto.CreateIODetail;
import org.example.dto.IODetail;

import java.util.List;

public interface IODetailInterface {
    String createIODetail(CreateIODetail dto);
    List<IODetail> getIODetailByItemId(String id);
    boolean deleteIODetail(String id);


}
