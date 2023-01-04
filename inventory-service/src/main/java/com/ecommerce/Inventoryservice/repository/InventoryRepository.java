package com.ecommerce.Inventoryservice.repository;

import com.ecommerce.Inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    Optional<Inventory> findByskuCode(String integer);
}
