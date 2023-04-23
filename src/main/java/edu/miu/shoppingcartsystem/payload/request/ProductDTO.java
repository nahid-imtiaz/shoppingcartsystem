package edu.miu.shoppingcartsystem.payload.request;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    int id;
    String name;
    Double sellingPrice;
}