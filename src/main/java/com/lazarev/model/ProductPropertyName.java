package com.lazarev.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductPropertyName {

    private long id;
    private String name;

    public ProductPropertyName() {
    }

    @Id
    @Column(name = "product_property_name_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPropertyName that = (ProductPropertyName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
