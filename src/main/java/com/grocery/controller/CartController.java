package com.grocery.controller;

import com.grocery.entity.Cart;
import com.grocery.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grocery/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/add-product")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Integer cartId,
            @RequestParam Integer productId,
            @RequestParam Integer quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @GetMapping("/{userId}/cart")
    public ResponseEntity<Cart> getCartForUser(@PathVariable Integer userId) {
        Cart cart = cartService.getCartForUser(userId);
        return ResponseEntity.ok(cart);
    }
}
