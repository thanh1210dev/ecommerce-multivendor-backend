package com.thanh.repository;

import com.thanh.domain.AccountStatus;
import com.thanh.model.Cart;
import com.thanh.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus status);

}
