package com.hardikit.pizzacompany.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<MenuItem> items;

    @OneToMany
    private List<Topping> toppings;

    private String paymentOption;

    private double totalAmount;

    private String status;

    // Constructors, getters, and setters
}
