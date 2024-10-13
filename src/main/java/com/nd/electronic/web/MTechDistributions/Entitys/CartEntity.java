package com.nd.electronic.web.MTechDistributions.Entitys;

import com.nd.electronic.web.MTechDistributions.DTOS.CartItemDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Carts")
public class CartEntity {

    @Id
    private String cartId;

    private Date cartdate;

    //One user having only one cart
    @OneToOne
    private UserEntity userEntity;
    //Mapping
    /***
     *  Cart having Lots of Items.
     *  req OneToMany with cartitem
     */
   @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<CartItemsEntity> cartList=new ArrayList<>();





}
