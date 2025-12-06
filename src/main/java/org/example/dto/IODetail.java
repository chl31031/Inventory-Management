package org.example.dto;

import java.sql.Date;

public record IODetail(
        String id,
        String inOut, //입고, 출고 표시
        Integer quantity,
        Date io_date,
        Manager manager,
        Item item
) {
}
