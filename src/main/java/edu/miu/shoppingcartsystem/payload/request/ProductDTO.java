package edu.miu.shoppingcartsystem.payload.request;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private int quantity;
    private Double actualPrice;
    private Double sellingPrice;
}