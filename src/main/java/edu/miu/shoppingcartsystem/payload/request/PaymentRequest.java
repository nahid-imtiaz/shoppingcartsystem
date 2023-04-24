package edu.miu.shoppingcartsystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest implements Serializable {
    private String cardNumber;
    private String nameOnCard;
    private String expiryDate;
    private int cvv;
    private int amount;

}
