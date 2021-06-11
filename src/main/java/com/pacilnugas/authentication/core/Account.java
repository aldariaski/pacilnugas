package com.pacilnugas.authentication.core;

import com.pacilnugas.activities.model.Matkul;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    private String username;

    @ManyToMany
    private List<Matkul> personalizedMatkul;

    private String password;
    private String type;


    public Account() {
    }

    public Account(String username, String password) {
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

    public void setType(String type) {
        this.type = type;
    }

    public void setPersonalizedMatkul(List<Matkul> personalizedMatkul) {
        this.personalizedMatkul = personalizedMatkul;
    }

    public List<Matkul> getPersonalizedMatkul() {
        return personalizedMatkul;
    }
}
