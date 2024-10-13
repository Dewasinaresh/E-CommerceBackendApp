package com.nd.electronic.web.MTechDistributions.Controllers;


import com.nd.electronic.web.MTechDistributions.DTOS.AddToCart;
import com.nd.electronic.web.MTechDistributions.DTOS.ApiResponseMassage;
import com.nd.electronic.web.MTechDistributions.DTOS.CartDTO;
import com.nd.electronic.web.MTechDistributions.DTOS.CartItemDTO;
import com.nd.electronic.web.MTechDistributions.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/carts")
public class CartController {


        @Autowired
        private CartService cartService;
        //add Item to cart
        @PostMapping("/{userId}")
        public ResponseEntity<CartDTO> addItemToCart(
                @PathVariable(name = "userId" ) String userId,
                @RequestBody AddToCart addToCart
        )
        {
            final CartDTO UserCart = cartService.addItemToCart(userId , addToCart);
            return new ResponseEntity<>(UserCart, HttpStatus.OK);
        }

        //Remove from cart
        @DeleteMapping("/{userId}/cartItem/rm/{cartItem}")
        public ResponseEntity<ApiResponseMassage> ItemRemoveFromCart(
                @PathVariable String userId,
                @PathVariable(name = "cartItem") int cartItemId
        )
        {
            cartService.removeFromCart(userId,cartItemId);
            final ApiResponseMassage APImassage = ApiResponseMassage.builder()
                    .massage("Item is removed successfully")
                    .success(true)
                    .HttpStatus(OK)
                    .build();
            return new ResponseEntity<>(APImassage,HttpStatus.OK);
        }

        @DeleteMapping("/{userId}/Cart/clr")
        public ResponseEntity<ApiResponseMassage> ClearCart
        (
            @PathVariable String userId
        )
        {
            cartService.clearCart(userId);
            final ApiResponseMassage APImassage = ApiResponseMassage.builder()
                    .massage("Now cart is clear successfully")
                    .success(true)
                    .HttpStatus(OK)
                    .build();
            return new ResponseEntity<>(APImassage,HttpStatus.OK);

        }

        @GetMapping("/{userId}/cart/getCart")
        public ResponseEntity<CartDTO> getCartByUser(
                @PathVariable String userId
        ){
            final CartDTO userCart = cartService.getCartByUser(userId);
            return new ResponseEntity<>(userCart,HttpStatus.OK);
        }


}
