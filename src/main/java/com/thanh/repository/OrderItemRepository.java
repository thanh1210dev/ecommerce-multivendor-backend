package com.thanh.repository;

import com.thanh.model.Address;
import com.thanh.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
