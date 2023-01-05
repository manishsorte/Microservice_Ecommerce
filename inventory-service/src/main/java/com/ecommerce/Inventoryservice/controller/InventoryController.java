package com.ecommerce.Inventoryservice.controller;

import com.ecommerce.Inventoryservice.payload.InventoryResponse;
import com.ecommerce.Inventoryservice.service.implementation.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> inStock(@RequestParam List<String> skuCode){
        List<InventoryResponse> inStock = this.inventoryService.inStock(skuCode);
        return new ResponseEntity<>(inStock,HttpStatus.OK);
    }
}
