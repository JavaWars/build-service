package com.lazarev.model;

import javax.persistence.*;

@Entity
public class ProductPropertyValue {

    private long id;
    private String productValue;

    public ProductPropertyValue() {
    }

    @Id @GeneratedValue
    @Column(name = "product_property_value_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique=true)
    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }
}
