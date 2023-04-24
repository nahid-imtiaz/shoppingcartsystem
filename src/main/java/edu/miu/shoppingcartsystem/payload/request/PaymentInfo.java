package edu.miu.shoppingcartsystem.payload.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PaymentInfo implements Serializable {
//    private String cardType;
    private String cardNumber;
    private String nameOnCard;
    private int cvv;
    private String expiryDate;
    private int amount;

}
