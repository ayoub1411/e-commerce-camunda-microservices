package com.orders.dto;

public record LineOrderRequest(
        int quantity,
        Long productId
) {}