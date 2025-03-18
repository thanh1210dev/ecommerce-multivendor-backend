package com.thanh.repository;

import com.thanh.model.Cart;
import com.thanh.model.CartItem;
import com.thanh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
