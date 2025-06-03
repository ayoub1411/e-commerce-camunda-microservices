package com.orchestor.camundaorchestor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CamundaOrchestorApplication {


    public static void main(String[] args) {
        SpringApplication.run(CamundaOrchestorApplication.class, args);
    }

    @Bean
    public ObjectMapper camundaObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
    @Bean
    CommandLineRunner runner(RuntimeService service){

        return args -> {

//
//
//            // camundaService.startUserActivationProcess("john");
//
//
//
//
//
//
//

            OrderDto order = new OrderDto(
                    1L,
                    123,
                    LocalDate.now(),
                    List.of(new LineOrderDto(1L, 2, 1L))
            );

//
//            // Start the process with proper serialization
//
//
//            Thread.sleep(10000);
//
//            System.out.println("waiting first 10 seconds is completed");
            ProcessInstance instance=service.startProcessInstanceByKey(
                    "Process_042xkxb",
                    Map.of("order", Variables.objectValue(order).create())
            );

//
//            Thread.sleep(10000);
//            System.out.println("waiting 10 sec for process instance is completed");
//
//
//
//// Wait until the process is finished or poll for status
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            boolean isValid= (boolean) service.getVariable(instance.getId(), "isValid");
////            List<FullOrderLineResponse> list = mapper.readValue(json, new TypeReference<List<FullOrderLineResponse>>() {});
//
//            System.out.println("isvalid"+isValid);








        };
    }
}
