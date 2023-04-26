package edu.miu.shoppingcartsystem.payload.response;

import edu.miu.shoppingcartsystem.model.Category;
import edu.miu.shoppingcartsystem.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
    public ProductResponse(Product p){
        this.id=p.getId();
        this.name = p.getName();
        this.image = p.getImage();
        this.actualPrice = p.getActualPrice();
        this.sellingPrice = p.getSellingPrice();
        this.quantity = p.getQuantity();
        this.isActive = p.getActive();
        if(p.getUser()!= null){
            this.userId=p.getUser().getId();
        }


        if(p.getCategory() != null){
            this.categoryId = p.getCategory().getId();
            this.categoryName = p.getCategory().getName();
        }
    }

    private Long id;
    private String name;
    private Double sellingPrice;
    private Double actualPrice;
    private int quantity;
    private Boolean isActive;
    private String image;
    private Long categoryId;
    private String categoryName;
    private Long userId=-1L;
}
