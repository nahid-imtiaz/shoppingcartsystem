package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.payload.request.OrderRequest;
import edu.miu.shoppingcartsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest){
        Order order = new Order(orderRequest.getAddress(), orderRequest.getPhone(),orderRequest.getTotalPrice(),orderRequest.getUser());
        order = orderRepository.save(order);
        return order;
    }
}
