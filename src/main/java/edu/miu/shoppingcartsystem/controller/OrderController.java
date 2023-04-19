package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public OrderService orderService;

    //get all data
    @GetMapping("/")
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    //get single data
    @GetMapping("/{orderId}")
    public Order getSingleOrder(@PathVariable int orderId){
       return orderService.getOrderById(orderId).orElseThrow(()-> new RuntimeException("This order id is not available"));
    }

    //post data
    @PostMapping("/")
    public Order pushNewOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    //delete order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId){
        orderService.deleteOrder(orderId);
    }

    //update order
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        try {
            Order updated = orderService.updateOrder(orderId, updatedOrder);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

