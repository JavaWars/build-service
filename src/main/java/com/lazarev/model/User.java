package com.lazarev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User
        implements Serializable //this Serializable need to save information about user
                                //for spring security when devtools restart application
{

    private long id;
    private Role role;
    private String name;
    private String secondName;
    private String password;
    private String email;
    private String adress;//can be changed to Adress
    private String phone;
    @JsonIgnore
    private List<TransactionOrder> userOrders;
    @JsonIgnore
    private Developer d;//if user is admin => for this developer

    public User() {
    }

    public User(User u) {
        this.setRole(u.getRole());
        this.setEmail(u.getEmail());
        this.setPhone(u.getPhone());
        this.setPassword(u.getPassword());
        this.setName(u.getName());
        this.setAdress(u.getAdress());
        this.setId(u.getId());
        this.setSecondName(u.getSecondName());
        this.setUserOrders(u.getUserOrders());
        this.setD(u.getD());
    }

    @Id @GeneratedValue
    @Column(name = "user_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @NotNull(message = "no role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @NotEmpty(message = "name cant be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @JsonIgnore
    @NotEmpty(message = "password cant be empty")
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "email is empty")
    @Column(name = "email",unique = true)
    @org.hibernate.validator.constraints.Email(message = "Invalid Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Column(name = "phone",unique = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_order_id")
    public List<TransactionOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<TransactionOrder> userOrders) {
        this.userOrders = userOrders;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "developer_admin",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "developer_id") }
    )
    public Developer getD() {
        return d;
    }

    public void setD(Developer d) {
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(adress, user.adress) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(userOrders, user.userOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, name, secondName, password, email, adress, phone, userOrders);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", userOrders=" + userOrders +
                '}';
    }
}
