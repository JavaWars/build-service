package com.lazarev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "activities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Activity {

    private long id;
    private String description;
    private double price;
    @JsonIgnore
    private Developer owner;

    public Activity() {
    }

    @Id @GeneratedValue
    @Column(name = "activity_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    public Developer getOwner() {
        return owner;
    }

    public void setOwner(Developer owner) {
        this.owner = owner;
    }
}
