package com.aise.mcnugu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_account", unique = true)
    private String account;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_nickname")
    private String nickname;

    @JsonIgnore
    @Column(name = "member_password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Home> myHomes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Guest> homes = new ArrayList<>();


    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }


    // implements userDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
