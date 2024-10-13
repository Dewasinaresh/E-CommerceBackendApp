package com.nd.electronic.web.MTechDistributions.Services;


import com.nd.electronic.web.MTechDistributions.DTOS.OrderDTO;
import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;

import java.util.List;

public interface OrderService {
    //Create
    OrderDTO createOrder(OrderDTO orderDTO,String userId,String cartId);
    //Remove
    void removeOrder(String orderId);

    //getByUser
    List<OrderDTO> getOrderByUser(String userId);


    //GetAll
    PageableResponse<OrderDTO> getAllOrder(int pageNumber,int pageSize,String SortBy);


}
