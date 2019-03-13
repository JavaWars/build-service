package com.lazarev.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "developers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Developer {

    private long id;
    private String adress;
    private String phone;
    private Date foundation;
    private String name;
    private String story;
    @JsonIgnore
    private User founder;

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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "d")//{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//            org.hibernate.annotations.CascadeType.DELETE,
//            org.hibernate.annotations.CascadeType.MERGE,
//            org.hibernate.annotations.CascadeType.PERSIST,
//            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
//    @JoinTable(name = "developer_admin",
//            joinColumns = { @JoinColumn(name = "developer_id") },
//            inverseJoinColumns = { @JoinColumn(name = "user_id") }
//    )
    public Set<User> getDeveloperAdminsSquade() {
        return developerAdminsSquade;
    }

    public void setDeveloperAdminsSquade(Set<User> developerAdminsSquade) {
        this.developerAdminsSquade = developerAdminsSquade;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "developer")
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

//    @NotBlank
    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void addAdmin(User u){developerAdminsSquade.add(u);}

    public void removeAdmin(User u){developerAdminsSquade.remove(u);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", foundation=" + foundation +
                ", name='" + name + '\'' +
                ", story='" + story + '\'' +
                ", activities=" + activities +
                '}';
    }
}
