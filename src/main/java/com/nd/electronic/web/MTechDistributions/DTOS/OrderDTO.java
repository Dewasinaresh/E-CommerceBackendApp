package com.nd.electronic.web.MTechDistributions.DTOS;


import com.nd.electronic.web.MTechDistributions.Entitys.OrderListEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private String orderId;
    private String orderStatus="Placed";
    private String paymentStatus="NotPaid";
    private int orderAmount;
    private String billingAdress;
    private String billingPhoneNumber;
    private String billingName;
    private Date orderDate=new Date();
    private Date deliveredDate;

    private List<OrderListDTO> ordersList=new ArrayList<>();


}
