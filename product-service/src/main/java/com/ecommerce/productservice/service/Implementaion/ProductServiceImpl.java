package com.ecommerce.productservice.service.Implementaion;

import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.payload.ProductDto;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product createdProduct = this.productRepository.save(this.dtoToProduct(productDto));
        return this.productToDto(createdProduct);
    }

    @Override
    public ProductDto getProduct(Integer productId) {
        Product retrievedProduct = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", productId));
        return this.productToDto(retrievedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> retrievedAllProducts = this.productRepository.findAll();
        List<ProductDto> productDtoList = retrievedAllProducts.stream()
                .map(this::productToDto)
                .toList();
        return productDtoList;
    }

    @Override
    public void deleteProduct(Integer productId) {
         this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", productId));
          this.productRepository.deleteById(productId);
    }

    private Product dtoToProduct(ProductDto productDto) {
        return this.modelMapper.map(productDto,Product.class);
    }

    private ProductDto productToDto(Product product) {
        return this.modelMapper.map(product, ProductDto.class);
    }
}
