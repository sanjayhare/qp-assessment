package com.grocery.service.impl;

import com.grocery.entity.Product;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProduct(String id) {
        Product product = productRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ProductID", id));
        return product;
    }

    public List<Product> insertAllProducts(List<Product> products) {
        List<Product> newProductList = new ArrayList<>();
        for (Product product : products) {
            System.out.println("before searching name:" + product.getProductName().trim());
            Product existingProduct = productRepository.findByProductName(product.getProductName().trim());
            if (existingProduct != null && existingProduct.getProductId() > 0) {
                System.out.println("in if loop");
                Long productQuantity = existingProduct.getProductQuantity() + product.getProductQuantity();
                existingProduct.setProductQuantity(productQuantity);
                Product updatedProduct = productRepository.save(existingProduct);
                newProductList.add(updatedProduct);
            } else {
                System.out.println("in else loop");
                Product insertedProduct = productRepository.save(product);
                newProductList.add(insertedProduct);
            }
        }
        return newProductList;
    }

    public boolean insertProduct(Product product) {
        boolean isInserted = false;
        Product insertedProduct = productRepository.save(product);
        if (insertedProduct != null && insertedProduct.getProductId() > 0) {
            isInserted = true;
        }
        return isInserted;
    }

    public boolean updateProduct(Product product) {
        boolean isProductUpdated = false;
        Long pId = product.getProductId();
        Product oldProduct = productRepository.findById(product.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ProductID", String.valueOf(pId)));
        if (oldProduct != null) {
            product = productRepository.save(product);
            isProductUpdated = true;
        }
        return isProductUpdated;
    }
}
