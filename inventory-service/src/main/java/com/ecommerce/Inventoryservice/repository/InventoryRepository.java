package com.ecommerce.Inventoryservice.repository;

import com.ecommerce.Inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    List<Inventory> findByskuCodeIn(List<String> integer);
}
