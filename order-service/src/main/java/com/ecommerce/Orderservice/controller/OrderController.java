package com.ecommerce.Orderservice.controller;

import com.ecommerce.Orderservice.exception.ApiResponse;
import com.ecommerce.Orderservice.payload.OrderDto;
import com.ecommerce.Orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
        OrderDto placedOrder = this.orderService.placeOrder(orderDto);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer orderId) {
        OrderDto placedOrder = this.orderService.getOrderById(orderId);
        return new ResponseEntity<>(placedOrder, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrderById(@PathVariable Integer orderId) {
        this.orderService.deleteOrder(orderId);
        return new ResponseEntity<>(new ApiResponse("Order deleted successfully", true), HttpStatus.ACCEPTED);
    }
}