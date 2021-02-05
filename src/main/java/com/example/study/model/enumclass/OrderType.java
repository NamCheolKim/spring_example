package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {

    ALL(0, "묶음배송", "모든상품 묶음 배송"),
    INDIVIDUAL(1, "개별배송", "준비된 상품 먼저 배송");

    private Integer id;
    private String title;
    private String description;

}
