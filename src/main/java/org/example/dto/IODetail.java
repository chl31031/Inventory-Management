package org.example.dto;

public record IODetail(
        String id, //전표 코드
        String inOut, //입고, 출고 표시
        Integer quantity, //입고, 출고 수량
        Manager manager, //참조하는 관리자 아이디
        Item item //참조하는 상품 아이디
) {
}
