package com.lazarev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "product")
public class Product {

    private long id;
    private String name;
    private String productDescription;
    private double price;
    @JsonIgnore
    private Developer developer;
    @JsonIgnore
    private Set<Category> categoryes=new HashSet<>();
    @JsonIgnore
    private Map<ProductPropertyName, ProductPropertyValue> productProperties=new HashMap<>();
    @JsonIgnore
    private List<Comment> comments=new LinkedList<>();

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
    public Map<ProductPropertyName, ProductPropertyValue> getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(Map<ProductPropertyName, ProductPropertyValue> productProperties) {
        this.productProperties = productProperties;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
