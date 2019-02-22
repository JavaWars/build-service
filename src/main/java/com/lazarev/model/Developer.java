package com.lazarev.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String adress;

    @NotBlank
    private String phone;

    private Date foundation;

//    private Set<User> developerAdminsSquade;
//
//    private Set<Product> products;
//
//    //services provided by developer
//    private Set<Activity> activities;

    public Developer() {
    }
}
