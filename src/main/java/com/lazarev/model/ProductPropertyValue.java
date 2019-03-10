package com.lazarev.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPropertyValue that = (ProductPropertyValue) o;
        return id == that.id &&
                Objects.equals(productValue, that.productValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productValue);
    }
}
