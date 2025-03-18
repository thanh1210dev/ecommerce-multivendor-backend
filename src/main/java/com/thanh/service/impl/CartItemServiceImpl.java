package com.thanh.service.impl;

import com.thanh.model.CartItem;
import com.thanh.model.User;
import com.thanh.repository.CartItemRepository;
import com.thanh.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

//    @Override
//    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
//        CartItem item = findCartItemById(id);
//        User cartItemUser = item.getCart().getUser();
//
//        if (cartItemUser.getId().equals(userId)) {
//            item.setQuantity(cartItem.getQuantity());
//            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
//            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
//            cartItemRepository.save(item);
//        }
//        throw new Exception("you can't update this cartItem");
//
//    }
@Override
public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
    CartItem item = findCartItemById(id);
    User cartItemUser = item.getCart().getUser();

    // Kiểm tra quyền truy cập trước khi cập nhật
    if (!cartItemUser.getId().equals(userId)) {
        throw new Exception("you can't update this cartItem");
    }

    // Cập nhật thông tin CartItem
    item.setQuantity(cartItem.getQuantity());
    item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
    item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());

    // Lưu lại CartItem
    return cartItemRepository.save(item);
}

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item = findCartItemById(cartItemId);
        User cartItemUser = item.getCart().getUser();
        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(item);
        }
        else throw new Exception("you can't delete this item");
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws Exception {
        return cartItemRepository.findById(cartItemId).orElseThrow(()->
                new Exception("cart item not found with id: " + cartItemId));
    }
}
