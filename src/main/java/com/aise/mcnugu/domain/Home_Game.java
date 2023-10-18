package com.aise.mcnugu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Entity
@Getter @Setter
@Table(name = "home_game")
public class Home_Game {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Home home;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member mc;

    // 실시간 socket 연결 url
    private URL realtimeUrl;
}
