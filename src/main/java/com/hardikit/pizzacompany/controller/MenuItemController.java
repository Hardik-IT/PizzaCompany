package com.hardikit.pizzacompany.controller;

import com.hardikit.pizzacompany.model.MenuItem;
import com.hardikit.pizzacompany.model.Topping;
import com.hardikit.pizzacompany.service.MenuItemService;
import com.hardikit.pizzacompany.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private ToppingService toppingService;

    @GetMapping
    public String getMenuItems(Model model) {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        model.addAttribute("menuItems", menuItems);
        List<Topping> toppings = toppingService.getAllToppings();
        model.addAttribute("toppings", toppings);
        return "menu-items";
    }

    @GetMapping("/add")
    public String addMenuItem(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        List<Topping> toppings = toppingService.getAllToppings();
        model.addAttribute("toppings", toppings);
        return "menu-item-add";
    }

    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute MenuItem menuItem, BindingResult result) {
        if (result.hasErrors()) {
            return "menu-item-add";
        }
        menuItemService.saveMenuItem(menuItem);
        return "redirect:/admin/menu-items";
    }

    @GetMapping("/edit/{id}")
    public String editMenuItem(@PathVariable Long id, Model model) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        List<Topping> toppings = toppingService.getAllToppings();
        model.addAttribute("menuItem", menuItem);
        model.addAttribute("toppings", toppings);
        model.addAttribute("toppingsInMenuItem", menuItem.getToppings().stream().map(Topping::getId).collect(Collectors.toList()));
        return "menu-item-edit";
    }

    @PostMapping("/edit/{id}")
    public String editMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem, BindingResult result) {
        if (result.hasErrors()) {
            return "menu-item-edit";
        }
        menuItem.setId(id); // Set the id of the menuItem
        menuItemService.saveMenuItem(menuItem);
        return "redirect:/admin/menu-items";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return "redirect:/admin/menu-items";
    }
}
