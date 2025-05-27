package com.orders.dao;

import com.orders.entities.LineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LineOrderRepository extends JpaRepository<LineOrder, Long> {
}
