package org.example.db;

import org.example.dto.Category;

import java.util.List;

public interface CategoryInterface {
    List<Category> getCategories();
    void createCategory(String name);
    void deleteCategory(String name);
    boolean existByName(String name);
    String getCategoryIdByName(String name);
}
