package com.thanh.controller;

import com.thanh.exceptions.ProductException;
import com.thanh.model.Cart;
import com.thanh.model.CartItem;
import com.thanh.model.Product;
import com.thanh.model.User;
import com.thanh.request.AddItemRequest;
import com.thanh.response.ApiResponse;
import com.thanh.service.CartItemService;
import com.thanh.service.CartService;
import com.thanh.service.ProductService;
import com.thanh.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization")String jwt
    ) throws  Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartService.findUserCart(user);

        System.out.println("cart - "+cart.getUser().getEmail());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddItemRequest req,
            @RequestHeader("Authorization")String jwt
    ) throws ProductException,Exception {
        User user = userService.findUserByJwtToken(jwt);
        if (user == null) {
            throw new ProductException("User not found");
        }

        Product product = productService.findProductById(req.getProductId());
        if (product == null) {
            throw new ProductException("Product not found");
        }
        CartItem item = cartService.addCartItem(user,
                product,
                req.getSize(),
                req.getQuantity());
        ApiResponse res = new ApiResponse();
        res.setMessage("Item Added To Cart Successfully");
        return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item Remove From Cart Successfully");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization")String jwt
    )throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        CartItem updatedCartItem = null;
        if (cartItem.getQuantity()>0){
            updatedCartItem= cartItemService.updateCartItem(user.getId(),
                    cartItemId,cartItem);
        }

        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
    }
}
