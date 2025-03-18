package com.thanh.service;

import com.thanh.model.Product;
import com.thanh.model.User;
import com.thanh.model.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
