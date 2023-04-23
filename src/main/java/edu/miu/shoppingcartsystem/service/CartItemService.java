package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    public Optional<CartItem> getCartItemById(int id) {
        return cartItemRepository.findById(id);
    }

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItemById(int id) {
        cartItemRepository.deleteById(id);
    }

    public List<CartItem> getCartItemsByOrderId(int orderId){
        return cartItemRepository.findByOrderId(orderId);
    }

    public List<CartItem> saveCartItem(List<CartItem> itemList ) {
        return cartItemRepository.saveAll(itemList);
    }
}
