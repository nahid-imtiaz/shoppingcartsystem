package edu.miu.shoppingcartsystem.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.shoppingcartsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Column(name = "house")
    String houseNo;
    String zip;
    String street;
    Date order_date;
    int quantity;
    float total_price;
    String discount_code;
    String status;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;
*/

}
