package com.orchestor.camundaorchestor;

public record LineOrderRequest(
        int quantity,
        Long productId
) {}