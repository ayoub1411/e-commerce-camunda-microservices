package com.orders.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer customerId;
@OneToMany(mappedBy = "order")
    List<LineOrder> lineOrders;

    LocalDate orderDate;



}
