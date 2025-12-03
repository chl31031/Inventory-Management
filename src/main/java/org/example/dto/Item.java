package org.example.dto;

public record Item(
        String id,
        String name,
        String category,
        Integer quantity
) {
}
