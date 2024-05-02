package com.nd.electronic.web.MTechDistributions.Services;

import com.nd.electronic.web.MTechDistributions.DTOS.AddToCart;
import com.nd.electronic.web.MTechDistributions.DTOS.CartDTO;

public interface CartService {

    /***
     * There some Business Logic for car
     *1.AddtoCard
     * 2.Remove from cart
     * 3.Clear cart
     */

    CartDTO addItemToCart(String userId, AddToCart addToCart);
    void removeFromCart(String userId,int cartItem);
    void clearCart(String userId);
    public CartDTO getCartByUser(String userId);


}
