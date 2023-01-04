package com.ecommerce.productservice.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException  extends RuntimeException{
    String resourceName;
    String fieldName;
    long id;

    public ResourceNotFoundException(String resourceName, String fieldName, long id) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,id));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;
    }
}
