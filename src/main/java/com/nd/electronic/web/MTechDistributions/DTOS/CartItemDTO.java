package com.nd.electronic.web.MTechDistributions.DTOS;


import com.nd.electronic.web.MTechDistributions.Entitys.CartEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {

    private int cartItemId;
    private ProductEntity productEntity;
    private int quantity;
    private int totalPrices;
    // Item is not req bcz its passing throw req
    //We remove it




}
