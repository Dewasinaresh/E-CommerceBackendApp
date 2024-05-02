package com.nd.electronic.web.MTechDistributions.DTOS;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCart {
    private String productId;
    private int quantity;
}
