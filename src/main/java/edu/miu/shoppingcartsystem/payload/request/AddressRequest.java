package edu.miu.shoppingcartsystem.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AddressRequest {

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
