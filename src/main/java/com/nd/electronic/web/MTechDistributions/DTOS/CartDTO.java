package com.nd.electronic.web.MTechDistributions.DTOS;

import com.nd.electronic.web.MTechDistributions.Entitys.CartEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO
{


    private String cartId;
    private Date cartdate;
    //One user having only one cart
    private UserEntity userEntity;
    List<CartEntity> cartList=new ArrayList<>();




}
