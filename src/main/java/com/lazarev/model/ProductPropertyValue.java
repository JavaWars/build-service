package com.lazarev.model;

import javax.persistence.*;

@Entity
public class ProductPropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String productValue;

    public ProductPropertyValue() {
    }
}
