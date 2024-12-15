package com.grocery.controller;

import com.grocery.constant.GroceryConstants;
import com.grocery.dto.ResponseDto;
import com.grocery.entity.Product;
import com.grocery.service.impl.ExcelReaderService;
import com.grocery.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/grocery/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ExcelReaderService excelReaderService;
    @Autowired
    private ProductService productService;

    //This is bulk quantity updalod mehtod in which we are reading excel file of product list and inserting
    // it into grcery intems table
    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> uploadProductsExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            List<Product> products = excelReaderService.readProductsFromExcel(file.getInputStream());
            if (products != null && products.size() > 0) {
                products = productService.insertAllProducts(products);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_201));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(GroceryConstants.STATUS_500, GroceryConstants.MESSAGE_500));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to process Excel file", e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody Product product) {
        boolean isproductUpdated = productService.updateProduct(product);

        if (isproductUpdated == true) {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(GroceryConstants.STATUS_417, GroceryConstants.MESSAGE_417_UPDATE));
        }
    }

    @GetMapping("/get/AllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/get/Product")
    public ResponseEntity<Product> getProduct(@RequestParam String pId) {
        Product product = productService.getProduct(pId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto>  createProduct(@RequestBody Product product) {
        boolean iProductInserted = productService.insertProduct(product);
        if (iProductInserted == true) {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(GroceryConstants.STATUS_417, GroceryConstants.MESSAGE_417_UPDATE));
        }
    }
}

