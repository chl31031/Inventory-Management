package org.example.view.viewcontroller.fake;

import org.example.view.model.*;
import org.example.view.viewcontroller.*;

import java.time.ZonedDateTime;
import java.util.*;

public class FakeViewControllerContainer implements ViewControllerContainer {

    private final ArrayList<Manager> managers = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
    private final HashMap<String, Category> categories = new HashMap<>();
    private final HashMap<String, ArrayList<ItemIO>> itemToItemIOList = new HashMap<>();

    public FakeViewControllerContainer() {
        managers.add(new Manager("id", "재우", "직급1"));

        var r = new Random();
        for (var i = 0; i < 5; i++) {
            var itemID = getRandomString(6);
            var categoryID = getRandomString(6);
            var initialQuantity = r.nextInt(10, 100);
            items.add(new Item(
                    itemID,
                    getRandomString(12),
                    categoryID,
                    categoryID,
                    initialQuantity
            ));
            categories.put(categoryID, new Category(categoryID, categoryID));

            itemToItemIOList.put(itemID, new ArrayList<>(List.of(new ItemIO(
                    getRandomString(6),
                    initialQuantity,
                    IO.IN,
                    "재우",
                    ZonedDateTime.now()
            ))));
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
                result = result.stream().filter(item -> Objects.equals(item.categoryID(), categoryID)).toList();
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

    @Override
    public CreateItem createItem() {
        return (name, categoryID) -> {
            items.addFirst(new Item(
                    getRandomString(6), name, categoryID, categoryID, 0
            ));
            categories.put(categoryID, new Category(categoryID, categoryID));
        };
    }

    @Override
    public GetItem getItem() {
        return id -> {
            for (var i = 0; i < items.size(); i++) {
                var item = items.get(i);
                if (item.id().equals(id)) {
                    return item;
                }
            }
            return null;
        };
    }

    @Override
    public GetItemIOList getItemIOList() {
        return (itemID, page) -> {
            if (!itemToItemIOList.containsKey(itemID)) {
                itemToItemIOList.put(itemID, new ArrayList<>());
            }
            var result = itemToItemIOList.get(itemID);
            return result.subList(Math.min(10 * page, result.size()), Math.min(10 * (page + 1), result.size()));
        };
    }

    @Override
    public CreateItemIO createItemIO() {
        return (itemID, io, quantity, managerID) -> {
            var manager = managers.stream().filter(m -> Objects.equals(m.id(), managerID)).findFirst().orElse(null);
            if (manager == null) {
                return;
            }

            for (var i = 0; i < items.size(); i++) {
                var item = items.get(i);
                if (item.id().equals(itemID)) {
                    items.remove(i);
                    var newQuantity = item.quantity();
                    if (io == IO.IN) {
                        newQuantity += quantity;
                    } else {
                        newQuantity -= quantity;
                    }
                    items.addFirst(new Item(
                            item.id(),
                            item.name(),
                            item.categoryID(),
                            item.category(),
                            newQuantity
                    ));

                    if (!itemToItemIOList.containsKey(itemID)) {
                        itemToItemIOList.put(itemID, new ArrayList<>());
                    }
                    itemToItemIOList.get(itemID).addFirst(new ItemIO(
                            getRandomString(6),
                            quantity,
                            io,
                            manager.name(),
                            ZonedDateTime.now()
                    ));
                    return;
                }
            }
        };
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
