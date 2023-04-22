package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.payload.request.AddressRequest;
import edu.miu.shoppingcartsystem.payload.request.OrderRequest;
import edu.miu.shoppingcartsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest){
        AddressRequest ar = orderRequest.getAddress();
        StringBuilder sb = new StringBuilder();
        sb.append(ar.getAddress()).append(", " ).append(ar.getCity()).append(", ").append(ar.getState()).append(", ").append(ar.getCountry()).append(", ").append(ar.getZipCode());
        Order order = new Order(sb.toString(), orderRequest.getPhone(),orderRequest.getTotalPrice(),orderRequest.getUser());
        order = orderRepository.save(order);
        return order;
    }
}
