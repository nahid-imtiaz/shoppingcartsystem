package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id){
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CartItem> saveCartItem(@RequestBody CartItem cartItem){
        CartItem createdCartItem = cartItemService.saveCartItem(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartItem);
    }

    /*
    * Updating the Cart Item
    * */

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable int id, @RequestBody CartItem cartItem){
        Optional<CartItem> existingCartItem = cartItemService.getCartItemById(id);

        if(existingCartItem.isPresent()){
            cartItem.setCart_id(id);
            CartItem updateCartItem = cartItemService.saveCartItem(cartItem);
            return ResponseEntity.ok(updateCartItem);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable int id){
        Optional<CartItem> toDeleteCartItem = cartItemService.getCartItemById(id);
        if(toDeleteCartItem.isPresent()){
            cartItemService.deleteCartItemById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
