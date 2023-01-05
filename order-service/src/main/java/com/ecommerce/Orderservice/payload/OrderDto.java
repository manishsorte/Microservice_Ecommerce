package com.ecommerce.Orderservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;
    private String orderNumber;
    private String price;
    List<OrderLineItemsDto> orderLineItemsDtoList;
}
