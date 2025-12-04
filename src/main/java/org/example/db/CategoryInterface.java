package org.example.db;

import java.util.List;

public interface CategoryInterface {
    List<String> getCategories();
    void createCategory(String name);
    void deleteCategory(String name);
    boolean existByName(String name);
}
