package org.example.dto;

public record IODetail(
        String id,
        String inOut,
        Integer quantity,
        Manager manager,
        Item item
) {
}
