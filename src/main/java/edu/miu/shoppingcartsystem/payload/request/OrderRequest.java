package edu.miu.shoppingcartsystem.payload.request;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequest {
    private AddressRequest address;
    private String phone;
    private double totalPrice;
    private User user;
    private List<CartItem> cartItemList;
    private PaymentInfo paymentInfo;

}
