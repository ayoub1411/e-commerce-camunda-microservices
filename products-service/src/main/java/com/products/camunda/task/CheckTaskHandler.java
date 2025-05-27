package com.products.camunda.task;

import com.products.dto.OrderDto;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Component
public class CheckTaskHandler implements ExternalTaskHandler {


    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {



        OrderDto order = (OrderDto) task.getVariable("order");

        System.out.println(order);
        service.complete(task);


    }
}
