package com.aise.mcnugu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "appliance")
public class Appliance {

    @Id @GeneratedValue
    @Column(name = "app_id")
    private Long id;

    @Column(name = "app_name")
    private String name;

    @Column(name = "app_type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Home home;
}
