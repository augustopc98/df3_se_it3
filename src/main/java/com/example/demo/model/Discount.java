package com.example.demo.model;

import java.math.BigDecimal;

public class Discount {

    private BigDecimal discountPercentage;

    public Discount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal applyDiscount(BigDecimal totalAmount) {
        return totalAmount.subtract(totalAmount.multiply(discountPercentage).divide(BigDecimal.valueOf(100)));
    }
}
