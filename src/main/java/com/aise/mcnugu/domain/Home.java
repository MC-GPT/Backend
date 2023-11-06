package com.aise.mcnugu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore // 양방향 참조시, json 응답 순환 => 한쪽 무시 처리
    @ManyToOne(fetch = FetchType.EAGER)
    private Member owner;

    @JsonIgnore
    @OneToMany(mappedBy = "home")
    private List<Guest> guests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "home")
    private List<Appliance> appliances = new ArrayList<>();


    // 삭제할거
    public String refreshCode() {
        code = Integer.toString((int)(Math.random() * 8999) + 1000) + owner.getId();
        guests.clear();
        // 로컬에 코드 다시 저장
        return code;
    }
}
