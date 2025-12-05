package org.example.view.screen;

import org.example.Main;

import java.util.Scanner;

public class ItemSearchScreen extends Screen {

    private final String search;

    public ItemSearchScreen(String search) {
        this.search = search;
    }

    private void printSearch() {
        var screenText = new StringBuilder();
        screenText.append("현재 검색어: ").append(search).append('\n');
        screenText.append("검색어 입력: ");
        System.out.print(screenText);
    }

    @Override
    public void action() {
        printSearch();
        var scanner = new Scanner(System.in);
        var search = scanner.nextLine();
        Main.screens.removeLast();
        Main.screens.getLast().setResult(ResultKey.SEARCH, search);
    }
}
