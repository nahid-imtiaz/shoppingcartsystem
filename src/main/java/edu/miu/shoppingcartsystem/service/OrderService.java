package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.model.EmailDetails;
import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.payload.request.AddressRequest;
import edu.miu.shoppingcartsystem.payload.request.OrderRequest;
import edu.miu.shoppingcartsystem.repository.CartItemRepository;
import edu.miu.shoppingcartsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    EmailService emailService;
    public Order createOrder(OrderRequest orderRequest){

        AddressRequest ar = orderRequest.getAddress();
        StringBuilder sb = new StringBuilder();
        sb.append(ar.getAddress()).append(", " ).append(ar.getCity()).append(", ").append(ar.getState()).append(", ").append(ar.getCountry()).append(", ").append(ar.getZipCode());
        Order order = new Order(sb.toString(), orderRequest.getPhone(),orderRequest.getTotalPrice(),orderRequest.getUser());
        order = orderRepository.save(order);

        // Need to send email to the user to notify user
        String recipient = orderRequest.getUser().getEmail();
        String msgBody ="";
        String subject = "Your order has placed. Order Ref: "+ order.getOrderId();
        String attachment="Dear "+ orderRequest.getUser().getUsername()+",";

      //  List<CartItem> cartItems = cartItemRepository.

        EmailDetails emailDetails = new EmailDetails(recipient,msgBody, subject, "");

        emailService.sendSimpleMail(emailDetails);



        return order;
    }
}
