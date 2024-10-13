package com.nd.electronic.web.MTechDistributions.Services.impl;

import com.nd.electronic.web.MTechDistributions.DTOS.OrderDTO;
import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.*;
import com.nd.electronic.web.MTechDistributions.Repositories.CartRepo;
import com.nd.electronic.web.MTechDistributions.Repositories.OrdersRepo;
import com.nd.electronic.web.MTechDistributions.Repositories.UserRepo;
import com.nd.electronic.web.MTechDistributions.Services.OrderService;
import com.nd.electronic.web.MTechDistributions.Utility.GeneratePageableResponse;
import com.nd.electronic.web.MTechDistributions.exceptionUtils.BadApiRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO, String userId,String cardId) {

        final UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User is not found "));
        final CartEntity cartEntity = cartRepo.findById(cardId).orElseThrow(() -> new RuntimeException("Cart is not found "));
        List<CartItemsEntity> cartItems = cartEntity.getCartList();

        if(cartItems.size()<=0)
            throw new BadApiRequest("Cart is an empty");
        orderDTO.setOrderId(UUID.randomUUID().toString());
        final OrdersEntity ordersEntity = modelMapper.map(orderDTO, OrdersEntity.class);
   
        //Convert cart items into
        AtomicReference<Integer>  totalOrderPrince=new AtomicReference<>(0);
       List<OrderListEntity> orderListEntities= cartItems.stream().map( cart ->{
        OrderListEntity ordersList=OrderListEntity.builder()
                .TotalQuantity(cart.getQuantity())
                .Amount(cart.getQuantity()*cart.getTotalPrices())
                .product(cart.getProductEntity())
                .orders(ordersEntity)

                .build();
        totalOrderPrince.set(totalOrderPrince.get()+ ordersList.getAmount());
           return ordersList;
               }
               ).collect(Collectors.toList());
        ordersEntity.setOrdersList(orderListEntities);
       ordersEntity.setOrderAmount(totalOrderPrince.get());

        cartEntity.getCartList().clear();
        cartRepo.save(cartEntity);
        ordersRepo.save(ordersEntity);

        return modelMapper.map(ordersEntity,OrderDTO.class);
    }

    @Override
    public void removeOrder(String orderId) {
        final OrdersEntity orderEntity = ordersRepo.findById(orderId).orElseThrow(()->new RuntimeException("order is not availble "));
        ordersRepo.delete(orderEntity);

    }

    @Override
    public List<OrderDTO> getOrderByUser(String userId)
    {
        final UserEntity userEntity = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User is not found by provided userId"));
        final List<OrdersEntity> orderList = ordersRepo.findByUserEntity(userEntity);
        final List<OrderDTO> orderListDto = orderList.stream().map(orders -> {
            return modelMapper.map(orders, OrderDTO.class);
        }).collect(Collectors.toList());
        return orderListDto;
    }

    @Override
    public PageableResponse<OrderDTO> getAllOrder(int pageNumber, int pageSize, String SortBy)
    {
        final Sort sortAscending = Sort.by(SortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortAscending);
        final Page<OrdersEntity> pageOrders = ordersRepo.findAll(pageable);
        final PageableResponse<OrderDTO> userDTOPageableResponse = GeneratePageableResponse.generatePageResponse(pageOrders, OrderDTO.class);
        return userDTOPageableResponse;


    }
}
