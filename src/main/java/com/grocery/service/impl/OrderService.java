package com.grocery.service.impl;


import com.grocery.entity.*;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CartRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.repository.ProductRepository;
import com.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository,ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;

    }
    public Order placeOrder(Integer userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserID", String.valueOf(userId)));
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        Order order = new Order();
        order.setUser(user);
        double totalAmount = 0.0;
        Product product = null;
        List<Product> products = new ArrayList<>();
        for (CartItem cartItem : cartItems)
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductName(cartItem.getProduct().getProductName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            order.getOrderItems().add(orderItem);
            totalAmount += cartItem.getPrice();

            product = cartItem.getProduct();
            System.out.println("product="+product.getProductId());
            product.setProductQuantity(product.getProductQuantity()- cartItem.getQuantity());
            products.add(product);
        }
        order.setTotalAmount(totalAmount);
        cart.getCartItems().clear();// Clear the cart after placing the order
        productRepository.saveAll(products);//updating product quantity
        Order placedOrder= orderRepository.save(order);
        return placedOrder;
    }
    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findByUser_PersonId(userId);
    }
}
