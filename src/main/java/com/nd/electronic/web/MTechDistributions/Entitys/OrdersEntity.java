package com.nd.electronic.web.MTechDistributions.Entitys;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Orders")
public class OrdersEntity {

    @Id
    private String orderId;
    //Placed, Dispatch, delivered
    private String orderStatus;
    // Non-Paid ,paid
    private String paymentStatus;
    private int orderAmount;
    @Column(length = 1000)
    private String billingAdress;
    private String billingPhoneNumber;
    private String billingName;
    private Date orderDate;
    private Date deliveredDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_Id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orders",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderListEntity> ordersList=new ArrayList<>();



}
