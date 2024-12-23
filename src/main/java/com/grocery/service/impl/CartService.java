package com.grocery.service.impl;

import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;
import com.grocery.entity.Product;
import com.grocery.exception.GroceryMessegeException;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CartItemRepository;
import com.grocery.repository.CartRepository;
import com.grocery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    /**
     * Add a product to the cart.
     * @param cartId The ID of the cart.
     * @param productId The ID of the product to add.
     * @param quantity The quantity of the product to add.
     */
    public void addProductToCart(Integer cartId, Integer productId, Integer quantity) {
        // Fetch the cart and product
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", String.valueOf(cartId)));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", String.valueOf(productId)));

        // Check if the product is already in the cart
        if(quantity!=null && quantity>product.getProductQuantity())
        {
            throw  new GroceryMessegeException("Product are out of stock ");
        }

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            // Update the quantity of the existing cart item
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setPrice(cartItem.getPrice() + (quantity* product.getProductPrice()));
            cartItemRepository.save(cartItem);
        } else {
            // Add a new cart item
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
            newCartItem.setPrice(quantity* product.getProductPrice());
            cartItemRepository.save(newCartItem);
        }
    }
    public Cart getCartForUser(Integer userId) {
        Cart cart= cartRepository.findByUser_PersonId(userId);
        return cart;
    }
}
