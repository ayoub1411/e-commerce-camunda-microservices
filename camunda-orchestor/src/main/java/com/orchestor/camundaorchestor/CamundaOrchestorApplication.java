package com.orchestor.camundaorchestor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.camunda.bpm.engine.RuntimeService;
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
    CommandLineRunner runner(RuntimeService service){

        return args -> {



            // camundaService.startUserActivationProcess("john");

            Thread.sleep(20000);





            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String orderJson = objectMapper.writeValueAsString(new OrderDto(
                    1L,
                    123,
                    LocalDate.now(),
                    List.of(new LineOrderDto(1L, 2, 1001L))
            ));

            ObjectValue orderValue = Variables
                    .serializedObjectValue(orderJson)
                    .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                    .objectTypeName(OrderDto.class.getName()) // optional but useful
                    .create();


            Map<String, Object> vars = new HashMap<>();
            vars.put("order", orderValue);



            service.startProcessInstanceByKey("Process_042xkxb",vars);




        };
    }
}
