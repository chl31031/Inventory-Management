package org.example.db;

import java.util.List;

public interface CategoryInterface {
    List<String> getCategories();
    void createCategory(String id);
    void deleteCategory(String id);
}
