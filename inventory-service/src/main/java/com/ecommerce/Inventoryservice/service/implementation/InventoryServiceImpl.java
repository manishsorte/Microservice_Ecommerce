package com.ecommerce.Inventoryservice.service.implementation;

import com.ecommerce.Inventoryservice.repository.InventoryRepository;
import com.ecommerce.Inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean inStock(String skuCode) {
        return this.inventoryRepository.findByskuCode(skuCode).isPresent();
    }
}
