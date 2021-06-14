package com.pacilnugas.account.core;

import com.pacilnugas.activities.model.Matkul;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    private String username;

    private String password;
    private String type;
    private boolean courseAccess;
    private boolean personalizedAccess;

    @ManyToMany
    private List<Matkul> personalizedMatkul;

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

    public boolean getCourseAccess() {
        return courseAccess;
    }

    public void setCourseAccess(boolean courseAccess) {
        this.courseAccess = courseAccess;
    }

    public boolean getPersonalizedAccess() {
        return personalizedAccess;
    }

    public void setPersonalizedAccess(boolean personalizedAccess) {
        this.personalizedAccess = personalizedAccess;
    }

    public List<Matkul> getPersonalizedMatkul() {
        return personalizedMatkul;
    }

    public void setPersonalizedMatkul(List<Matkul> personalizedMatkul) {
        this.personalizedMatkul = personalizedMatkul;
    }
}
