package com.thanh.repository;

import com.thanh.model.Address;
import com.thanh.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
//    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    List<Product> findBySellerId(Long sellerId);

    @Query("select p from Product p where (:query is null or lower(p.title)" +
            "like lower(concat('%', :query, '%')))"+
            "or (:query is null or lower(p.category.name)"+
            "like lower(concat('%', :query, '%') ) )")
    List<Product> searchProduct(@Param("query") String query);
}
