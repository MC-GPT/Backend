package com.aise.mcnugu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "game")
public class Game {

    @Id @GeneratedValue
    @JsonIgnore
    @Column(name = "game_id")
    private Long id;

    @Column(name = "game_name")
    private String name;

    @ManyToOne
    private Member mc;
}
