package com.orders.camunda.worker;

import com.orders.camunda.task.OrderSaverHandler;
import com.orders.service.OrderService;


import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.stereotype.Component;

@Component
public class OrderSaverWorker {
    OrderSaverHandler orderSaverHandler;

    public OrderSaverWorker(OrderSaverHandler orderSaverHandler) {

        this.orderSaverHandler = orderSaverHandler;



    }

    @PostConstruct
    public void subscribe() {

        System.out.println("Check products subscribe");
        ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .lockDuration(1000)
                .build()

                .subscribe("save_order")

                .handler(orderSaverHandler).open();


    }

}
