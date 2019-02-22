package com.lazarev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    private long id;
    private String categoryName;
    private String description;
    private Set<Product> productsInCategory;

    public Category() {
    }

    @Id
    @Column(name = "category_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "Developer_Product",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    public Set<Product> getProductsInCategory() {
        return productsInCategory;
    }

    public void setProductsInCategory(Set<Product> productsInCategory) {
        this.productsInCategory = productsInCategory;
    }
}
