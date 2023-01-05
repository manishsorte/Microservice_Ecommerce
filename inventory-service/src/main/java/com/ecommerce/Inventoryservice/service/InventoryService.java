package com.ecommerce.Inventoryservice.service;

import com.ecommerce.Inventoryservice.payload.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> inStock(List<String> skuCode);
}
