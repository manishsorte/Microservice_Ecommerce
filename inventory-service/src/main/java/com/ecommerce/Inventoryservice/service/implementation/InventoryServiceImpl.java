package com.ecommerce.Inventoryservice.service.implementation;

import com.ecommerce.Inventoryservice.model.Inventory;
import com.ecommerce.Inventoryservice.payload.InventoryResponse;
import com.ecommerce.Inventoryservice.repository.InventoryRepository;
import com.ecommerce.Inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> inStock(List<String> skuCode) {
        return this.inventoryRepository.findByskuCodeIn(skuCode)
                .stream()
                .map(this::getSkuCodeDetails).toList();
    }

    private InventoryResponse getSkuCodeDetails(Inventory inventory){
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .inStock(inventory.getQuantity() > 0)
                .build();
    }
}
