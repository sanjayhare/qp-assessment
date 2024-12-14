package com.grocery.service.impl;


import com.grocery.entity.*;
import com.grocery.repository.CartRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public Order placeOrder(Integer userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();

        Order order = new Order();
        order.setUser(user);

        double totalAmount = 0.0;

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductName(cartItem.getProduct().getProductName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            order.getOrderItems().add(orderItem);
            totalAmount += cartItem.getPrice() * cartItem.getQuantity();
        }
        order.setTotalAmount(totalAmount);
        cart.getCartItems().clear(); // Clear the cart after placing the order

        return orderRepository.save(order);
    }
}
