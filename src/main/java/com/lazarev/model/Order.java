package com.lazarev.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_order")
public class Order {

    private long id;
    private User user;
    private List<Product> orderedProduct;

    private double totalCount;

    private String status;//can be changed to Status later

    public Order() {
    }

    @Id
    @Column(name = "order_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "developer_id") }
    )
    public List<Product> getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(List<Product> orderedProduct) {
        this.orderedProduct = orderedProduct;
    }
}
