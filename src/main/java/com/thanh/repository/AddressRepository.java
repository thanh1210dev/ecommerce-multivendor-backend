package com.thanh.repository;

import com.thanh.model.Address;
import com.thanh.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
