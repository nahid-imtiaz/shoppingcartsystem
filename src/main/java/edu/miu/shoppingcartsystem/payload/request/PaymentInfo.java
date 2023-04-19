package edu.miu.shoppingcartsystem.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentInfo {
    private String cardType;
    private String cardNumber;
    private String nameCard;
    private String cvv;
    private String expiryDate;
}
