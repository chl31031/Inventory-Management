package org.example.dto;

import java.time.LocalDate;

public record CreateIODetail(
        String managerId,
        String itemId,
        Integer quantity,
        LocalDate date,
        InAndOut io
) {
    enum InAndOut{
    IN,
    OUT
    }
}
