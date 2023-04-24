package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.model.Order;
import edu.miu.shoppingcartsystem.payload.request.OrderRequest;
import edu.miu.shoppingcartsystem.payload.request.PaymentInfo;
import edu.miu.shoppingcartsystem.payload.request.PaymentRequest;
import edu.miu.shoppingcartsystem.payload.response.MessageResponse;
import edu.miu.shoppingcartsystem.service.CartItemService;
import edu.miu.shoppingcartsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemService cartItemService;

    @PostMapping(value ="/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> placeOrder(@RequestBody OrderRequest orderRequest) {

        List<CartItem> cList = orderRequest.getCartItemList();
        double sum = cList.stream().mapToDouble(c->c.getPrice() * c.getQuantity()).sum();
        //make rest call to payment getway with the payment info
        if(callPaymentGateWay(orderRequest,sum).getMessage().equals("Success")){
            orderRequest.setTotalPrice(sum);
            Order order = orderService.createOrder(orderRequest);
            cList.stream().map(c -> {c.setOrder(order);
                return c;
            }).collect(Collectors.toList());

            cartItemService.saveCartItem(cList);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Success: Order created Successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new MessageResponse("Payment Failed!!!"));
        }

    }

    private MessageResponse callPaymentGateWay(OrderRequest orderRequest, double sum){
        RestTemplate restTemplate = new RestTemplate();
        PaymentInfo paymentInfo = orderRequest.getPaymentInfo();
        paymentInfo.setAmount((int)sum);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentInfo> request = new HttpEntity<>(paymentInfo,headers);
        request.getBody();
        String pament_url = "https://payment-gateway-384617.uc.r.appspot.com/api/card/payment";
        ResponseEntity<MessageResponse> response = restTemplate
                .exchange(pament_url, HttpMethod.POST, request, MessageResponse.class);
        return  response.getBody();
    }

}
