package com.orchestor.camundaorchestor;

public record LineOrderDto(
        Long id,
        int quantity,
        Long productId
) {}