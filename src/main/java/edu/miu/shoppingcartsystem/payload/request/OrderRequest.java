package edu.miu.shoppingcartsystem.payload.request;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.model.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequest {
    private AddressRequest address;
    private String email;
    private double totalPrice;
    private List<CartItem> cartItemList;
    private PaymentInfo paymentInfo;

}
