package com.orders.mapper;




import com.orders.dto.LineOrderDto;
import com.orders.dto.LineOrderRequest;
import com.orders.dto.OrderDto;
import com.orders.dto.OrderRequest;
import com.orders.entities.LineOrder;
import com.orders.entities.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order) {
        if (order == null) return null;

        List<LineOrderDto> lineOrderDtos = order.getLineOrders() != null
                ? order.getLineOrders().stream()
                .map(this::toLineOrderDto)
                .collect(Collectors.toList())
                : null;

        return new OrderDto(
                order.getId(),
                order.getCustomerId(),
                order.getOrderDate(),
                lineOrderDtos
        );
    }

    public Order toEntity(OrderRequest request) {
        if (request == null) return null;

        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setOrderDate(request.orderDate());


        List<LineOrder> lineOrders = request.lineOrders() != null
                ? request.lineOrders().stream()
                .map(req -> toLineOrderEntity(req, order))
                .collect(Collectors.toList())
                : null;

        order.setLineOrders(lineOrders);
        return order;
    }

    public LineOrderDto toLineOrderDto(LineOrder lineOrder) {
        if (lineOrder == null) return null;

        return new LineOrderDto(
                lineOrder.getId(),
                lineOrder.getQuantity(),
                lineOrder.getProductId()
        );
    }

    public LineOrder toLineOrderEntity(LineOrderRequest request, Order order) {
        if (request == null) return null;

        LineOrder lineOrder = new LineOrder();
        lineOrder.setOrder(order);
        lineOrder.setQuantity(request.quantity());
        lineOrder.setProductId(request.productId());

        return lineOrder;
    }
}
