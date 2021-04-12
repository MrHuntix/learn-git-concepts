package com.pun.poc.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetailBuilder {
    private String username;
    private String password;
    private List<? extends GrantedAuthority> authorities;

    public static MyUserDetailBuilder getBuilder() {
        return new MyUserDetailBuilder();
    }

    public MyUserDetailBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUserDetailBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public MyUserDetailBuilder setAuthorities(String authorities) {
        this.authorities = Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return this;
    }

    public MyUserDetail build() {
        return new MyUserDetail(this.username, this.password, this.authorities);
    }
}
