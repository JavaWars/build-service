package com.lazarev.model;

import javax.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    //can be not set
    @Column
    private double price;

// description


    public Activity() {
    }
}
