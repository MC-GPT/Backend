package com.aise.mcnugu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "home")
public class Home {

    @Id @GeneratedValue
    @Column(name = "home_id")
    private Long id;

    @Column(name = "home_name")
    private String name;

    @Column(name = "home_code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    @OneToMany(mappedBy = "home")
    private List<Guest> guests = new ArrayList<>();

    @OneToMany(mappedBy = "home")
    private List<Appliance> appliances = new ArrayList<>();
}
