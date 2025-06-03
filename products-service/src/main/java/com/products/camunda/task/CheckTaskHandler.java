package com.products.camunda.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.products.dto.FullOrderLineResponse;
import com.products.dto.OrderDto;
import com.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CheckTaskHandler implements ExternalTaskHandler {

 private final ProductService productService;

    @SneakyThrows
    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {


        ObjectValue typedValue = task.getVariableTyped("order", false);
        String json = typedValue.getValueSerialized();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Map<String,Object> vars = new HashMap<>();

        List<FullOrderLineResponse> fullOrderLineResponses =new ArrayList<>();
        OrderDto order = objectMapper.readValue(json, OrderDto.class);
        try{

            fullOrderLineResponses=productService.purchaseOrder(order.lineOrders());


        } catch (Exception e) {

            System.out.println(e.getMessage());

            vars.put("isValid",false);
            vars.put("errorMessage",e.getMessage());


            service.complete(task, vars);

return ;

        }

        vars.put("isValid",true);
        vars.put("fullLinesOrder",fullOrderLineResponses);

        service.complete(task, vars);


    }
}
