package com.lazarev.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //ref
//    private User user;

    @Column(length = 300)
    private String text;

    @CreatedDate
    private Date commentDate;

    //ref to product
//    private Product product;

    public Comment() {
    }
}
