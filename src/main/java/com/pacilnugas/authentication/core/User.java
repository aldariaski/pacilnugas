package com.pacilnugas.authentication.core;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
public abstract class User {
    @Id
    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "type", nullable = false)
    protected String type;

    @Column(name = "inputAccess", nullable = false)
    protected boolean inputAccess;

    @Column(name = "personalizedAccess", nullable = false)
    protected boolean personalizedAccess;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}
