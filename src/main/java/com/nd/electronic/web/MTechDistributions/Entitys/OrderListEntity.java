package com.nd.electronic.web.MTechDistributions.Entitys;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "OrderList")
public class OrderListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderListId;

    @OneToOne
    @JoinColumn(name = "product_Id")
    private ProductEntity product;

    private int TotalQuantity;
    private int Amount;

    @ManyToOne
    @JoinColumn(name = "order_Id")
    private OrdersEntity orders;

}
