package com.thanh.controller;

import com.thanh.model.Product;
import com.thanh.model.User;
import com.thanh.model.Wishlist;
import com.thanh.service.ProductService;
import com.thanh.service.UserService;
import com.thanh.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;


    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(
            @PathVariable Long productId,
            @RequestHeader("Authorization")String jwt
    )throws Exception {
    Product product = productService.findProductById(productId);
    User user = userService.findUserByJwtToken(jwt);
    Wishlist updatedWishlist = wishlistService.addProductToWishlist(
            user,
            product
    );
    return ResponseEntity.ok(updatedWishlist);
    }
}
