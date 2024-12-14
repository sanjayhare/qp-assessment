package com.grocery.controller;

import com.grocery.entity.Order;
import com.grocery.entity.OrderItem;

import com.grocery.service.impl.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Integer userId) {
        Order order = orderService.placeOrder(userId);
        return ResponseEntity.ok(order);
    }
}
