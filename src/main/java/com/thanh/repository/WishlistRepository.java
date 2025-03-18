package com.thanh.repository;

import com.thanh.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    Wishlist findByUserId(Long userId);
}
