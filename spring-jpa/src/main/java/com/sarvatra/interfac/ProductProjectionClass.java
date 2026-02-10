package com.sarvatra.interfac;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductProjectionClass {

    private String productType;
    private Integer quantity;

    @Override
    public String toString() {
        return "{productType='" + productType + '\'' +
               ", quantity=" + quantity + '}';
    }
}
