package com.picobankapi.poc.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "username")
    @Getter
    @NotNull
    private String username;

    @Column(name = "password")
    @Getter
    @NotNull
    private String password;

    @Column(name = "alias")
    @Getter
    @NotNull
    private String alias;

    public User(String username, String password, String alias) {
        this.username = username;
        this.password = password;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ",username=" + username + ",pwd=" + password + ",alias=" + alias + "]";
    }
}
