package com.aise.mcnugu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_password")
    private String password;

    // jwt token
    @Column(name = "member_token")
    private String token;

    @OneToMany(mappedBy = "owner")
    private List<Home> myHomes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Guest> homes = new ArrayList<>();
}
