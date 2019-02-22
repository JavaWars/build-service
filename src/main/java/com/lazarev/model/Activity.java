package com.lazarev.model;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    private long id;
    private String description;
    private double price;

    public Activity() {
    }

    @Id
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
}
