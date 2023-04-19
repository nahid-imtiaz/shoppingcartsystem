package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.model.Product;
import edu.miu.shoppingcartsystem.model.User;
import edu.miu.shoppingcartsystem.repository.OrderRepository;
import edu.miu.shoppingcartsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public UserRepository userRepository;



    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int order_id){
        return orderRepository.findById(order_id);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(int id){
         orderRepository.deleteById(id);
    }

//    public Order updateOrder(int id, Order order){
//        User user= userRepository.findById((long) id).orElseThrow(()-> new RuntimeException("User Not found"));
//        Order existorder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Id not doud"));
//        existorder.setOrder_id(order.getOrder_id());
//        existorder.setUser_id((user));
//        return orderRepository.save(existorder);

//        Order updateOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Id not doud"));
//        updateOrder.setUser_id(order.getUser_id());
//    }

    public Order updateOrder(int orderId, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setHouseNo(updatedOrder.getHouseNo());
            existingOrder.setZip(updatedOrder.getZip());
            existingOrder.setStreet(updatedOrder.getStreet());
            existingOrder.setOrder_date(updatedOrder.getOrder_date());
            existingOrder.setQuantity(updatedOrder.getQuantity());
            existingOrder.setTotal_price(updatedOrder.getTotal_price());
            existingOrder.setDiscount_code(updatedOrder.getDiscount_code());
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setUser_id(updatedOrder.getUser_id());

            return orderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }


//    public Order getOrderByUser(Long userId){
//        User user= userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not found"));
//
//    }

}

