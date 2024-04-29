package com.hardikit.pizzacompany.service;

import com.hardikit.pizzacompany.model.Topping;
import com.hardikit.pizzacompany.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {

    @Autowired
    private ToppingRepository toppingRepository;

    public List<Topping> getAllToppings() {
        return toppingRepository.findAll();
    }

    public Topping getToppingById(Long id) {
        return toppingRepository.findById(id).orElseThrow();
    }

    public Topping saveTopping(Topping topping) {
        toppingRepository.save(topping);
        return topping;
    }

    public void deleteTopping(Long id) {
        toppingRepository.deleteById(id);
    }
}