package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.exception.ApiResponse;
import com.ecommerce.productservice.payload.ProductDto;
import com.ecommerce.productservice.service.Implementaion.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = this.productServiceImpl.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId) {
        ProductDto retrievedProduct = this.productServiceImpl.getProduct(productId);
        return new ResponseEntity<>(retrievedProduct, HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> retrievedAllProduct = this.productServiceImpl.getAllProducts();
        return new ResponseEntity<>(retrievedAllProduct, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) {
        this.productServiceImpl.deleteProduct(productId);
        return new ResponseEntity<>(new ApiResponse("Product deleted successfully", true), HttpStatus.ACCEPTED);
    }
}
