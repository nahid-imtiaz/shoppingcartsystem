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

        StringBuilder sbBody = new StringBuilder();

        sbBody.append("Dear "+ orderRequest.getUser().getUsername()+",\n")
                 .append("Your order reference is: "+order.getOrderId()+"\n")
                 .append("<table><tr>")
                 .append("<td>Id</td><td>Name</td><td>Qty</td><td>Total</td></tr>");

        List<CartItem> cartItems = cartItemRepository.findByOrderId(order.getOrderId().intValue());
        int total = 0;
        for (CartItem c:cartItems){
            sbBody.append("<tr><td>"+c.getCartId()+"</td>")
                    .append("<td>"+c.getProduct().getName()+"</td>")
                    .append("<td>"+c.getQuantity()+"</td>")
                    .append("<td>"+(c.getQuantity() * c.getPrice())+"</td></tr>");
                total += (c.getQuantity() * c.getPrice());
        }

        sbBody.append("<tr><td colspan='3'></td><td>"+total+"</td>").append("</tr></table>");

        sbBody.append("Thank you").append("\n")
                .append("Easy store team");

        EmailDetails emailDetails = new EmailDetails(recipient,sb.toString(), subject, "");
        emailService.sendSimpleMail(emailDetails);
        return order;
    }
}
