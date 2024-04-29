package com.hardikit.pizzacompany.repository;

import com.hardikit.pizzacompany.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping,Long> {
}
