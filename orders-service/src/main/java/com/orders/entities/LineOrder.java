package com.orders.entities;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import jdk.jfr.MemoryAddress;
import lombok.Data;

@Entity
@Data

public class LineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
@ManyToOne
    Order order;

    int quantity;

    Long productId;


}
