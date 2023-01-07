package com.ecommerce.Orderservice.controller;

import com.ecommerce.Orderservice.exception.ApiResponse;
import com.ecommerce.Orderservice.payload.OrderDto;
import com.ecommerce.Orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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
    @CircuitBreaker(name = "inventoryBreaker", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventoryTimeOut")
    @Retry(name="inventoryRetry")
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
    public ResponseEntity<OrderDto> fallbackMethod(OrderDto orderDto, Exception runtimeException) {
        OrderDto failedOrderDto = new OrderDto();
        failedOrderDto.setId(0);
        failedOrderDto.setOrderNumber("Dummy Number");
        failedOrderDto.setPrice("Dummy Price");
        return new ResponseEntity<>(failedOrderDto,HttpStatus.GATEWAY_TIMEOUT);
    }
}
