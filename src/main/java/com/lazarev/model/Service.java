package com.lazarev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "services")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Service {

    private long id;
    @JsonIgnore
    private Set<Developer> developers;

    public Service() {
    }

    @Id 	@GeneratedValue
    @Column(name = "service_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "service")//fetch = FetchType.LAZY
    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", developers=" + developers +
                '}';
    }
}
