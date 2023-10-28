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

    @JsonIgnore
    @Column(name = "app_serial", unique = true)
    private String serialNumber;

    @Column(name = "app_name")
    private String name;

    @Column(name = "app_light")
    private boolean isLight;

    // 어짜피 기능 구현 안할건데 필요할까?
    // 0 - 냉장고, 1 - 에어컨, 2 - 홈브류
    @Column(name = "app_type")
    private int type;

    @Column(name = "app_locked")
    private boolean locked;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Home home;
}
