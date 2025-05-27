package com.products.dto;

public record LineOrderDto(
        Long id,
        int quantity,
        Long productId
) {}