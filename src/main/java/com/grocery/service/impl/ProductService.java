package com.grocery.service.impl;

import com.grocery.entity.Product;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts()
    {
        List<Product> products =  productRepository.findAll();
        return products;
    }
    public Product getProduct(String id)
    {
        Product product=  productRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ProductID", id));
        return product;
    }
    public List<Product> insertProducts(List<Product> products)
    {
        productRepository.saveAll(products);
        return products;
    }
    public boolean updateProduct(Product product)
    {
        boolean isProductUpdated = false;
        Long  pId = product.getProductId();
        Product oldProduct=  productRepository.findById(product.getProductId()).orElseThrow(
               () -> new ResourceNotFoundException("Product", "ProductID", String.valueOf(pId)));
        if(oldProduct!=null)
        {
            product = productRepository.save(product);
            isProductUpdated =true;
        }
        return isProductUpdated;
    }
}
