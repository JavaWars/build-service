package com.lazarev.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_order")
public class Order {

    @Id
    private long id;

//    private User user;

//    private List<Product> orderedProduct;

    private double totalCount;

    private String status;//can be changed to Status later

    public Order() {
    }
}
