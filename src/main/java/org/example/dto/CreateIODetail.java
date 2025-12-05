package org.example.dto;

import java.time.LocalDate;

public record CreateIODetail(
        String managerId,
        String itemId,
        Integer quantity,
        LocalDate date, //입고, 출고 날짜
        InAndOut io
) {
    public enum InAndOut{
    IN,
    OUT
    }
}
