package com.ecommerce.productservice.service;

import com.ecommerce.productservice.payload.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(Integer productId);

    List<ProductDto> getAllProducts();

    void deleteProduct(Integer productId);

}
