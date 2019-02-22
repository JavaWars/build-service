package com.lazarev.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private Role role;

    private String name;

    private String secondName;

    private String password;

    private String email;

    private String adress;//can be changed to Adress

    private String phone;

//    private List<Order> userOrders;


    public User() {
    }


}
