package com.orders;



import com.orders.dto.LineOrderRequest;
import com.orders.dto.OrderRequest;
import com.orders.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    private final Random random = new Random();

    @Bean
    public CommandLineRunner initOrders(OrderService orderService) {
        return args -> {

        };
    }

    private Long getRandomProductId() {
        // You can improve this by querying the DB for actual product IDs if needed
        return (long) (random.nextInt(6) + 1); // Assuming product IDs are from 1 to 6
    }
}
