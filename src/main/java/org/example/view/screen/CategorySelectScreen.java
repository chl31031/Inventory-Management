package org.example.view.screen;

import org.example.Main;
import org.example.view.model.Category;
import org.example.view.viewcontroller.GetCategoryList;

import java.util.List;
import java.util.Scanner;

public class CategorySelectScreen extends Screen {

    private final GetCategoryList getCategoryList = Main.viewControllerContainer.getCategoryList();
    private List<Category> categoryList = null;

    private void printCategories() {
        var screenText = new StringBuilder();
        for (var i = 0; i < categoryList.size(); i++) {
            var category = categoryList.get(i);
            screenText.append(i + 1).append(". ");
            screenText.append(category.name()).append(' ');
        }
        screenText.append("(e: 취소, x: 선택 제거, 숫자: 카테고리 선택)");
        System.out.println(screenText);
    }

    @Override
    public void action() {
        categoryList = getCategoryList.execute();
        if (categoryList == null) {
            return;
        }

        printCategories();
        var scanner = new Scanner(System.in);
        var selected = scanner.nextLine();
        if (selected.equalsIgnoreCase("e")) {
            Main.screens.removeLast();
            return;
        } else if (selected.equalsIgnoreCase("x")) {
            Main.screens.removeLast();
            Main.screens.getLast().setResult(ResultKey.CATEGORY_ID, null);
            return;
        }
        var selectedInt = Integer.parseInt(selected) - 1;
        if (selectedInt >= 0 && selectedInt < categoryList.size()) {
            Main.screens.removeLast();
            Main.screens.getLast().setResult(ResultKey.CATEGORY_ID, categoryList.get(selectedInt).id());
            return;
        }
    }
}
