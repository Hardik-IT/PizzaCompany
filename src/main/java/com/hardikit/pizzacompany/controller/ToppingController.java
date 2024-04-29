package com.hardikit.pizzacompany.controller;

import com.hardikit.pizzacompany.model.Topping;
import com.hardikit.pizzacompany.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin/toppings")
public class ToppingController {

    @Autowired
    private ToppingService toppingService;

    @GetMapping
    public String getAllToppings(Model model) {
        List<Topping> toppings = toppingService.getAllToppings();
        model.addAttribute("toppings", toppings);
        return "toppings";
    }

    @GetMapping("/add")
    public String addToppingForm(Model model) {
        Topping topping = new Topping();
        model.addAttribute("topping", topping);
        return "topping-add";
    }

    @PostMapping("/add")
    public String addTopping(@ModelAttribute Topping topping, Model model) {
        toppingService.saveTopping(topping);
        return "redirect:/admin/toppings";
    }

    @GetMapping("/edit/{id}")
    public String editToppingForm(@PathVariable Long id, Model model) {
        Topping topping = toppingService.getToppingById(id);
        model.addAttribute("topping", topping);
        return "topping-edit";
    }

    @PostMapping("/edit/{id}")
    public String editTopping(@PathVariable Long id, @ModelAttribute Topping topping, Model model) {
        Topping existingTopping = toppingService.getToppingById(id);
        existingTopping.setName(topping.getName());
        toppingService.saveTopping(existingTopping);
        return "redirect:/admin/toppings";
    }

    @GetMapping("/delete/{id}")
    public String deleteTopping(@PathVariable Long id) {
        toppingService.deleteTopping(id);
        return "redirect:/admin/toppings";
    }
}