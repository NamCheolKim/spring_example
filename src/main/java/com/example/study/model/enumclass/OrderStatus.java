package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    WAITING(0, "대기", "배송 대기 상태"),
    SENDING(1, "배송중", "배송중 상태"),
    COMPLETE(2, "배송완료", "배송완료 상태");

    private Integer id;
    private String title;
    private String description;
}

