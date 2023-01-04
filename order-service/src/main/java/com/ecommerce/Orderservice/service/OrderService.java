package com.ecommerce.Orderservice.service;

import com.ecommerce.Orderservice.model.Order;
import com.ecommerce.Orderservice.payload.OrderDto;

public interface OrderService {

    OrderDto getOrderById(Integer id);

    OrderDto placeOrder(OrderDto orderDto);

    void deleteOrder(Integer id);
}
