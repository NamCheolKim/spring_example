package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType {

    ACCOUNT_TRANSFER(0, "계좌이체", "계좌이체 결제"),
    CREDIT_CARD(1, "신용카드", "신용카드 결제");

    private Integer id;
    private String title;
    private String description;


}
