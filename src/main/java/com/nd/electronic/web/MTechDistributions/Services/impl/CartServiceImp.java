package com.nd.electronic.web.MTechDistributions.Services.impl;


import com.nd.electronic.web.MTechDistributions.DTOS.AddToCart;
import com.nd.electronic.web.MTechDistributions.DTOS.CartDTO;
import com.nd.electronic.web.MTechDistributions.DTOS.CartItemDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.CartEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.CartItemsEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.ProductEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import com.nd.electronic.web.MTechDistributions.Repositories.CartItemRepo;
import com.nd.electronic.web.MTechDistributions.Repositories.CartRepo;
import com.nd.electronic.web.MTechDistributions.Repositories.ProductRepo;
import com.nd.electronic.web.MTechDistributions.Repositories.UserRepo;
import com.nd.electronic.web.MTechDistributions.Services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public  CartDTO addItemToCart(String userId, AddToCart addToCart) {
        String productId=addToCart.getProductId();
        int productQnt=addToCart.getQuantity();
        ProductEntity productDetails=productRepo.findById(String.valueOf(productId)).orElseThrow(()->new RuntimeException("Product is not fount for Id : "+productId));
        final UserEntity userDetails = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user is not found for Id :" + userId));
        CartEntity cart=null;
        try{
           cart = cartRepo.findByUserEntity(userDetails);
        }
        catch (NoSuchElementException e)
        {
            cart = new CartEntity();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCartdate(new Date());
        }
        List<CartItemsEntity> cartList = cart.getCartList();
        AtomicBoolean updated= new AtomicBoolean(false);
        List<CartItemsEntity> updatedList = cartList.stream().map(
                Item -> {

                    if (Item.getProductEntity().getId().equals(productId)) {

                        Item.setQuantity(productQnt);
                        Item.setTotalPrices(productQnt * productDetails.getPrice());
                        updated.set(true);


                    }
                    return Item;
                }
        ).collect(Collectors.toList());
        cart.setCartList(updatedList);

        //perform cart operation
        if(!updated.get()){
            CartItemDTO cartItemDTO=CartItemDTO.builder()
                    .quantity(productQnt)
                    .totalPrices(productDetails.getPrice()*productQnt)
                    .productEntity(productDetails)
                    .build();
            CartItemsEntity cartItemsEntity=mapper.map(cartItemDTO,CartItemsEntity.class);
            cart.getCartList().add(cartItemsEntity);
            cart.setUserEntity(userDetails);

        }
        final CartEntity updatedCart = cartRepo.save(cart);
        return mapper.map(updatedCart,CartDTO.class);
    }

    @Override
    public void removeFromCart(String userId, int cartItem) {
        final CartItemsEntity cartItems = cartItemRepo.findById(cartItem).orElseThrow(() -> new RuntimeException("Item is not found in this item"));
        cartItemRepo.delete(cartItems);


    }

    @Override
    public void clearCart(String userId) {

        final UserEntity userDetails = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        final CartEntity cartDetails = cartRepo.findByUserEntity(userDetails);
        cartDetails.getCartList().clear();
        final CartEntity updatedCart = cartRepo.save(cartDetails);


    }
    @Override
    public CartDTO getCartByUser(String userId){
        final UserEntity userDetails = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        final CartEntity cartDetails = cartRepo.findByUserEntity(userDetails);
        return mapper.map(cartDetails,CartDTO.class);
    }

}
