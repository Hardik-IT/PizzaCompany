package com.hardikit.pizzacompany.repository;

import com.hardikit.pizzacompany.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
