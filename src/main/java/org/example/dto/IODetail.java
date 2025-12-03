package org.example.dto;

public record IODetail(
        String Id,
        String InOut,
        Integer Quantity,
        Manager manager,
        Item item
) {
}
