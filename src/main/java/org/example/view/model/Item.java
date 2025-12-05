package org.example.view.model;

public record Item(
        String id,
        String name,
        String categoryID,
        String category,
        int quantity
) {
}
