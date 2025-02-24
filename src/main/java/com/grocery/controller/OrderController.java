package com.grocery.controller;

import com.grocery.constant.GroceryConstants;
import com.grocery.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> placeOrder(@PathVariable Integer userId) {
        Order order = orderService.placeOrder(userId);
        return ResponseEntity.ok(new ResponseDto(GroceryConstants.STATUS_201,GroceryConstants.ORDER_MESSAGE_201));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Integer userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }
}
