package com.lazarev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoryName;

    private String description;

    //ref to Product
//    private Set<Product> productsInCategory;


    public Category() {
    }
}
