package com.products.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        Long id,
        Integer customerId,
        LocalDate orderDate,
        List<LineOrderDto> lineOrders
) {}