package com.nd.electronic.web.MTechDistributions.Controllers;

import com.nd.electronic.web.MTechDistributions.DTOS.OrderDTO;
import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create
    @PostMapping("/{userId}/{cartId}")
    public ResponseEntity<OrderDTO> createOrder(
            @PathVariable(name = "userId") String userId,
            @PathVariable(name = "cartId") String cartId,
            @RequestBody OrderDTO orderDTO
    ){
        final OrderDTO ordersDto = orderService.createOrder(orderDTO, userId, cartId);
        return new ResponseEntity<>(ordersDto,HttpStatus.CREATED);
    }
    //remove
    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteOrder(
            @RequestParam(name ="orderId") String orderId
    ){
        orderService.removeOrder(orderId);
        return new ResponseEntity<>("Order is removed of order Id"+orderId,HttpStatus.OK);
   }

    //getById
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrderById(
            @PathVariable(name = "userId" )String userId
    )
    {
        final List<OrderDTO> orderByUser = orderService.getOrderByUser(userId);
        return new ResponseEntity<>(orderByUser, HttpStatus.OK);
    }
    //getAll
    @GetMapping("/findAllOrder")
    public ResponseEntity<PageableResponse<OrderDTO>> getAllOrder(
            @RequestParam(value = "PageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "PageSize",defaultValue = "2",required = false) int pageSize,
            @RequestParam(value = "SortBy",defaultValue = "name",required = false) String sortBy
            )
    {
        final PageableResponse<OrderDTO> responseOrder = orderService.getAllOrder(pageNumber, pageSize, sortBy);
        return new  ResponseEntity<>(responseOrder,HttpStatus.OK);
             }




}
