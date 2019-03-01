package com.lazarev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    private long id;
    private String name;
    private double price;
    @JsonIgnore
    private Developer developer;
    @JsonIgnore
    private Set<Category> categoryes;
    @JsonIgnore
    private Map<ProductPropertyName, ProductPropertyValue> productDescription;
    @JsonIgnore
    private List<Comment> comments;
//orders for this product
    public Product() {
    }

    @Id    @GeneratedValue
    @Column(name = "product_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "Product_Category",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    public Set<Category> getCategoryes() {
        return categoryes;
    }

    public void setCategoryes(Set<Category> categoryes) {
        this.categoryes = categoryes;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_property",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_property_value_id") }
    )
    @MapKeyJoinColumn(name = "product_property_name_id")
    public Map<ProductPropertyName, ProductPropertyValue> getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Map<ProductPropertyName, ProductPropertyValue> productDescription) {
        this.productDescription = productDescription;
    }
}
