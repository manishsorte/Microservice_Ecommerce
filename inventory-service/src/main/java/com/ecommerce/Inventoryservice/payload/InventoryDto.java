package com.ecommerce.Inventoryservice.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private int id;
    private String skuCode;
    private String price;
    private Integer quantity;
}
