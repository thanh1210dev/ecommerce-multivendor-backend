package com.thanh.service.impl;

import com.thanh.model.Cart;
import com.thanh.model.CartItem;
import com.thanh.model.Product;
import com.thanh.model.User;

import com.thanh.repository.CartItemRepository;
import com.thanh.repository.CartRepository;
import com.thanh.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

//    @Override
//    public CartItem addCartItem(User user, Product product, String size, int quantity) {
//        Cart cart = findUserCart(user);
//        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);
//        if (isPresent != null) {
//            CartItem cartItem = new CartItem();
//            cartItem.setProduct(product);
//            cartItem.setQuantity(quantity);
//            cartItem.setUserId(user.getId());
//            cartItem.setSize(size);
//
//            int totalPrice = quantity*product.getSellingPrice();
//            cartItem.setSellingPrice(totalPrice);
//            cartItem.setMrpPrice(quantity*product.getMrpPrice());
//            cart.getCartItems().add(cartItem);
//            cartItem.setCart(cart);
//            return cartItemRepository.save(cartItem);
//        }
//        return isPresent;
//    }
@Override
public CartItem addCartItem(User user, Product product, String size, int quantity) {
    Cart cart = findUserCart(user);

    // Kiểm tra nếu giỏ hàng không tồn tại
    if (cart == null) {
        cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);
    }

    // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng
    CartItem existingItem = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

    if (existingItem != null) {
        // Cập nhật số lượng nếu sản phẩm đã tồn tại
        existingItem.setQuantity(existingItem.getQuantity() + quantity);
        int totalSellingPrice = existingItem.getQuantity() * product.getSellingPrice();
        existingItem.setSellingPrice(totalSellingPrice);
        existingItem.setMrpPrice(existingItem.getQuantity() * product.getMrpPrice());
        return cartItemRepository.save(existingItem);
    } else {
        // Tạo mới nếu sản phẩm chưa có trong giỏ hàng
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUserId(user.getId());
        cartItem.setSize(size);

        int totalPrice = quantity * product.getSellingPrice();
        cartItem.setSellingPrice(totalPrice);
        cartItem.setMrpPrice(quantity * product.getMrpPrice());
        cart.getCartItems().add(cartItem);
        cartItem.setCart(cart);
        return cartItemRepository.save(cartItem);
    }
}


    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalItem =0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice+=cartItem.getMrpPrice();
            totalDiscountPrice+=cartItem.getSellingPrice();
            totalItem+=cartItem.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountPrice));
        cart.setTotalItem(totalItem);        
        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            return 0;
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int) discountPercentage;
    }
}
