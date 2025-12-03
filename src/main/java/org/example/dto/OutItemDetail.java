package org.example.dto;

import java.util.List;

public record OutItemDetail(
        Item item,
        List<IODetail> ioDetailList
) {
}
