package com.ecommerce.Orderservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    private int OrderLineId;
    private String skuCode;
    private String price;
    private int quantity;

}
