package com.aise.mcnugu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "game")
public class Game {

    @Id @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @Column(name = "game_name")
    private String name;


    @OneToMany // 단방향 참조라 문제가 생길수 있음!
    private List<Appliance> usingApps = new ArrayList<>();
}
