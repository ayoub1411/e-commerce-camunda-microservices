package com.orders.camunda.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.orders.dto.FullOrderLineResponse;
import com.orders.dto.OrderDto;
import com.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderSaverHandler implements ExternalTaskHandler {


    final OrderService orderService;

    @SneakyThrows
    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {


        ObjectValue typedValue = task.getVariableTyped("fullLinesOrder", false);
        ObjectValue order = task.getVariableTyped("order", false);
        String json = typedValue.getValueSerialized();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<FullOrderLineResponse> fullOrderLineResponses=objectMapper.readValue(json,new TypeReference<List<FullOrderLineResponse>>() {});

        String orderJson=order.getValueSerialized();

        OrderDto orderDto=objectMapper.readValue(orderJson,OrderDto.class);


        orderService.createOrder(orderDto);
        System.out.println("Order Saved Successfully");


        System.out.println("Complete order info is here "+fullOrderLineResponses);


        service.complete(task);


    }
}
