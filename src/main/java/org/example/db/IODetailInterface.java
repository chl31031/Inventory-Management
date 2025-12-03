package org.example.db;

import org.example.dto.CreateIODetail;

public interface IODetailInterface {
    void createIODetail(CreateIODetail createIODetail);
    void deleteIODetail(String id);
}
