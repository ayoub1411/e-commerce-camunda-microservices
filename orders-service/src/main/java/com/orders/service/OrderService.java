package com.orders.service;

import com.orders.dto.OrderDto;
import com.orders.dto.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderRequest request);
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders();
}