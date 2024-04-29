package com.hardikit.pizzacompany.repository;

import com.hardikit.pizzacompany.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository  extends JpaRepository<MenuItem,Long> {
}
