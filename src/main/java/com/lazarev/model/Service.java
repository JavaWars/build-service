package com.lazarev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
//    private Set<Developer> developers;


    public Service() {
    }
}
