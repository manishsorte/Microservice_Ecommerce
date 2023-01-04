package com.ecommerce.Inventoryservice.controller;

import com.ecommerce.Inventoryservice.exception.ApiResponse;
import com.ecommerce.Inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public ResponseEntity<ApiResponse> inStock(@PathVariable String skuCode){
        boolean inStock = this.inventoryService.inStock(skuCode);
        return new ResponseEntity<>(new ApiResponse("Product which you are looking for is in stock!!",true),
                HttpStatus.FOUND);
    }
}
