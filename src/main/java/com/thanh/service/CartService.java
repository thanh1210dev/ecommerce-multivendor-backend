package com.thanh.service;

import com.thanh.model.Cart;
import com.thanh.model.CartItem;
import com.thanh.model.Product;
import com.thanh.model.User;

public interface CartService {
    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
            );
    public Cart findUserCart(User user);
}
