package com.nd.electronic.web.MTechDistributions.Entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Cart_Items")
public class CartItemsEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int cartItemId;
    @OneToOne
    @JoinColumn(name ="PRODUCT_ID")
    private ProductEntity productEntity;
    private int quantity;
    private int totalPrices;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private CartEntity cart;


}
