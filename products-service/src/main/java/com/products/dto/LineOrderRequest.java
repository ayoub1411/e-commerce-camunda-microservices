package com.products.dto;

public record LineOrderRequest(
        int quantity,
        Long productId
) {}