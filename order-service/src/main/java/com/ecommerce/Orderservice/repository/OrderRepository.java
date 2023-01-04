package com.ecommerce.Orderservice.repository;

import com.ecommerce.Orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
