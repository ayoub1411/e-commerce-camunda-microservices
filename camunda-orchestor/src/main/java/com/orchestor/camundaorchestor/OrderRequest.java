package com.orchestor.camundaorchestor;




import java.time.LocalDate;
import java.util.List;

public record OrderRequest(
        Integer customerId,
        LocalDate orderDate,
        List<LineOrderRequest> lineOrders
) {}