package com.sarvatra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestItemDto {
    private Long productId;
    private Integer quantity;
}
