package com.ecommerce.Orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String orderNumber;
    private String skuCode;
    private String price;

    @OneToMany(cascade = CascadeType.ALL)
    List<OrderLineItems> orderLineItemsList;
}
