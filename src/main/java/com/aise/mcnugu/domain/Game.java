package com.aise.mcnugu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "game")
public class Game {

    @Id @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @Column(name = "game_name")
    private String name;
}
