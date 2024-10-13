package com.nd.electronic.web.MTechDistributions.DTOS;

import com.nd.electronic.web.MTechDistributions.Entitys.OrdersEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListDTO {

    private int orderListId;
    private ProductEntity product;
    private int TotalQuantity;
    private int Amount;
    private OrdersEntity orders;



}
