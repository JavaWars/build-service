package com.lazarev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "services")
public class Service {

    private long id;

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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }
}
