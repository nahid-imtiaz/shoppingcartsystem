package edu.miu.shoppingcartsystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySaleReportDTO {
    private String name;
    private long total_quantity;
    private double total_price;
}
