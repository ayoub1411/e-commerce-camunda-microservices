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
            for (int i = 1; i <= 4; i++) {
                int customerId = random.nextInt(1000) + 1;

                List<LineOrderRequest> lines = List.of(
                        new LineOrderRequest(random.nextInt(5) + 1, getRandomProductId()),
                        new LineOrderRequest(random.nextInt(5) + 1, getRandomProductId())
                );

                OrderRequest orderRequest = new OrderRequest(
                        customerId,
                        LocalDate.now().minusDays(random.nextInt(10)),
                        lines
                );

                orderService.createOrder(orderRequest);
            }
        };
    }

    private Long getRandomProductId() {
        // You can improve this by querying the DB for actual product IDs if needed
        return (long) (random.nextInt(6) + 1); // Assuming product IDs are from 1 to 6
    }
}
