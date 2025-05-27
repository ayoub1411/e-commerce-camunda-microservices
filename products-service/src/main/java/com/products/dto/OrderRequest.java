package com.products.dto;



import java.time.LocalDate;
import java.util.List;

public record OrderRequest(
        Integer customerId,
        LocalDate orderDate,
        List<LineOrderRequest> lineOrders
) {}