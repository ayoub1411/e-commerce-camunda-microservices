package com.orders.service;



import com.orders.dao.OrderRepository;
import com.orders.dto.OrderDto;
import com.orders.dto.OrderRequest;
import com.orders.entities.LineOrder;
import com.orders.entities.Order;
import com.orders.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final
    OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto createOrder(OrderDto request) {
        Order order =new Order();
        order.setLineOrders(request.lineOrders()
                .stream().map(o->

                {
                    LineOrder tmp=new LineOrder();;
                    tmp.setQuantity(o.quantity());
                    tmp.setProductId(o.productId());
                    return tmp;

                }).toList());

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
