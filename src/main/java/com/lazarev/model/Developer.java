package com.lazarev.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "developers")
public class Developer {

    private long id;
    private String adress;
    private String phone;
    private Date foundation;
    @JsonIgnore
    private Service service;
    @JsonIgnore
    private Set<User> developerAdminsSquade=new HashSet<>();
    @JsonIgnore
    private Set<Product> products=new HashSet<>();
    @JsonIgnore
    private Set<Activity> activities=new TreeSet<>();    //services provided by developer

    public Developer() {
    }

    @Id    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "developer_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotBlank
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @NotBlank
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getFoundation() {
        return foundation;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developer_admin",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    public Set<User> getDeveloperAdminsSquade() {
        return developerAdminsSquade;
    }

    public void setDeveloperAdminsSquade(Set<User> developerAdminsSquade) {
        this.developerAdminsSquade = developerAdminsSquade;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "developer")
//    @JoinColumn(name = "product_id")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", foundation=" + foundation +
                ", service=" + service +
                ", developerAdminsSquade=" + developerAdminsSquade +
                ", products=" + products +
                ", activities=" + activities +
                '}';
    }
}
