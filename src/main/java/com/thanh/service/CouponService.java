package com.thanh.service;

import com.thanh.model.Coupon;
import com.thanh.model.Cart;
import com.thanh.model.User;
import lombok.extern.java.Log;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code,double orderValue, User user) throws Exception;
    Cart removeCoupon(String code,User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupons();
    void deleteCoupon(Long couponId) throws Exception;
}
