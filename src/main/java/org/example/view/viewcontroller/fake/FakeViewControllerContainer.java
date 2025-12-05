package org.example.view.viewcontroller.fake;

import org.example.view.model.Category;
import org.example.view.model.Item;
import org.example.view.model.Manager;
import org.example.view.viewcontroller.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class FakeViewControllerContainer implements ViewControllerContainer {

    private final ArrayList<Manager> managers = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
    private final HashMap<String, Category> categories = new HashMap<>();

    public FakeViewControllerContainer() {
        managers.add(new Manager("id", "재우", "직급1"));

        var r = new Random();
        for (var i = 0; i < 5; i++) {
            items.add(new Item(
                    getRandomString(6),
                    getRandomString(12),
                    getRandomString(6),
                    getRandomString(4),
                    r.nextInt(10, 100)
            ));
        }
    }

    @Override
    public GetManagerList getManagerList() {
        return () -> managers;
    }

    @Override
    public CreateManager createManager() {
        return (name, grade) -> {
            managers.addFirst(new Manager(
                    getRandomString(6), name, grade
            ));
        };
    }

    @Override
    public GetItemList getItemList() {
        return (page, search, categoryID) -> {
            var result = items.stream().toList();
            if (categoryID != null) {
                result = result.stream().filter(item -> Objects.equals(item.category(), categoryID)).toList();
            }
            if (search != null && !search.isBlank()) {
                result = result.stream().filter(item -> item.name().contains(search)).toList();
            }

            return result.subList(Math.min(10 * page, result.size()), Math.min(10 * (page + 1), result.size()));
        };
    }

    @Override
    public CreateCategory createCategory() {
        return (name) -> {
            var id = getRandomString(6);
            categories.put(id, new Category(id, name));
        };
    }

    @Override
    public GetCategoryList getCategoryList() {
        return () -> categories.values().stream().toList();
    }

    private String getRandomString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        var random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
