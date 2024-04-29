package com.hardikit.pizzacompany.controller;

import com.hardikit.pizzacompany.model.MenuItem;
import com.hardikit.pizzacompany.model.Orders;
import com.hardikit.pizzacompany.model.Topping;
import com.hardikit.pizzacompany.service.MenuItemService;
import com.hardikit.pizzacompany.service.OrderService;
import com.hardikit.pizzacompany.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private ToppingService toppingService;

    @GetMapping
    public String getAllOrders(Model model) {
        List<Orders> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String addOrderForm(Model model) {
        Orders order = new Orders();
        model.addAttribute("order", order);
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        model.addAttribute("menuItems", menuItems);
        List<Topping> toppings = toppingService.getAllToppings();
        model.addAttribute("toppings", toppings);
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Orders order, Model model) {
        orderService.saveOrder(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        Orders order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        model.addAttribute("menuItems", menuItems);
        List<Topping> toppings = toppingService.getAllToppings();
        model.addAttribute("toppings", toppings);
        return "order-edit";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, @ModelAttribute Orders order, Model model) {
        order.setId(id);
        orderService.saveOrder(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }
}