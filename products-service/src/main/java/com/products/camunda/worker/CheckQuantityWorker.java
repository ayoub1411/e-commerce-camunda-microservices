package com.products.camunda.worker;

import com.products.camunda.task.CheckTaskHandler;
import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Component
public class CheckQuantityWorker {
    CheckTaskHandler taskHandler;

    public CheckQuantityWorker(CheckTaskHandler taskHandler) {

        this.taskHandler = taskHandler;


    }

    @PostConstruct
    public void subscribe() {

        System.out.println("Check products subscribe");
        ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .lockDuration(1000)
                .build()

                .subscribe("check_quantity")

                .handler(taskHandler).open();


    }

}
