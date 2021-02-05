package com.example.study.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailApiResponse {

    private Long id;
    private String status;
    private LocalDate arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long orderGroupId;
    private Long itemId;
}
