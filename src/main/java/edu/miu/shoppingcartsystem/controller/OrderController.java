package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.payload.request.OrderRequest;
import edu.miu.shoppingcartsystem.payload.response.MessageResponse;
import edu.miu.shoppingcartsystem.service.CartItemService;
import edu.miu.shoppingcartsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemService cartItemService;

    @PostMapping("/placeOrder")
    public ResponseEntity<MessageResponse> placeOrder(@RequestBody OrderRequest orderRequest) {

        List<CartItem> cList = orderRequest.getCartItemList();
        double sum = cList.stream().mapToDouble(c->c.getPrice() * c.getQuantity()).sum();
        orderRequest.setTotalPrice(sum);
        Order order = orderService.createOrder(orderRequest);
        cList.stream().map(c -> {c.setOrder(order);
            return c;
        }).collect(Collectors.toList());

        cartItemService.saveCartItem(cList);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Success: Order created Successfully"));
    }

}
