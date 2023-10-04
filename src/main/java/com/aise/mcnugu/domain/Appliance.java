package com.aise.mcnugu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "app_serial", unique = true)
    private String serialNumber;

    @Column(name = "app_name")
    private String name;

    @Column(name = "app_type")
    private boolean isLight;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Home home;
}
