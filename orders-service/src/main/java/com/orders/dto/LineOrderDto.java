package com.orders.dto;

public record LineOrderDto(
        Long id,
        int quantity,
        Long productId
) {}