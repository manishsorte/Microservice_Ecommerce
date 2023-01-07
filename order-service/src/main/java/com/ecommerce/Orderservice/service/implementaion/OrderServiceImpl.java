package com.ecommerce.Orderservice.service.implementaion;

import com.ecommerce.Orderservice.client.InventoryResponse;
import com.ecommerce.Orderservice.exception.ResourceNotFoundException;
import com.ecommerce.Orderservice.model.Order;
import com.ecommerce.Orderservice.model.OrderLineItems;
import com.ecommerce.Orderservice.payload.OrderDto;
import com.ecommerce.Orderservice.payload.OrderLineItemsDto;
import com.ecommerce.Orderservice.repository.OrderRepository;
import com.ecommerce.Orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public OrderDto getOrderById(Integer orderId) {
        Order retrievedOrder = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", orderId));
        OrderDto retrieved = this.orderToDto(retrievedOrder);
        retrieved.setOrderLineItemsDtoList(mapToOrderLineItemsDto(retrievedOrder));
        return retrieved;
    }

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {

        List<String> skuCodeList = orderDto.getOrderLineItemsDtoList()
                .stream()
                .map(OrderLineItemsDto::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponse = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponse != null;
        boolean productInStock = Arrays.stream(inventoryResponse)
                .allMatch(InventoryResponse::isInStock);

        if (productInStock) {

            Order mapToOrder = new Order();
            mapToOrder.setOrderNumber(UUID.randomUUID().toString());
            mapToOrder.setOrderLineItemsList(mapToOrderLineItems(orderDto));
            mapToOrder.setPrice(orderDto.getPrice());

            Order placedOrder = this.orderRepository.save(mapToOrder);
            OrderDto dto = this.orderToDto(placedOrder);
            dto.setOrderLineItemsDtoList(mapToOrderLineItemsDto(placedOrder));
            return dto;
        } else {
            throw new IllegalArgumentException("Product is not in stock!!");
        }
    }

    @Override
    public void deleteOrder(Integer orderId) {
        this.orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", orderId));
        this.orderRepository.deleteById(orderId);
    }

    private Order dtoToOrder(OrderDto orderDto) {
        return this.modelMapper.map(orderDto, Order.class);
    }

    private OrderDto orderToDto(Order order) {
        return this.modelMapper.map(order, OrderDto.class);
    }

    private OrderLineItems dtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return this.modelMapper.map(orderLineItemsDto, OrderLineItems.class);
    }

    private OrderLineItemsDto dtoToOrderLineItemsDto(OrderLineItems orderLineItems) {
        return this.modelMapper.map(orderLineItems, OrderLineItemsDto.class);
    }

    private List<OrderLineItems> mapToOrderLineItems(OrderDto orderDto) {
        return orderDto.getOrderLineItemsDtoList()
                .stream()
                .map(this::dtoToOrderLineItems).toList();
    }

    private List<OrderLineItemsDto> mapToOrderLineItemsDto(Order order) {
        return order.getOrderLineItemsList()
                .stream()
                .map(this::dtoToOrderLineItemsDto).toList();
    }
}
