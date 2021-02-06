package com.example.study.model.network.request;

import com.example.study.model.enumclass.OrderStatus;
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
public class OrderDetailApiRequest {

    private Long id;
    private OrderStatus status;
    private LocalDate arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long orderGroupId;
    private Long itemId;
}
