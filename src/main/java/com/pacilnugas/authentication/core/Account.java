package com.pacilnugas.authentication.core;

import com.pacilnugas.activities.model.Matkul;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    private String username;

    private String password;
    private String type;
//    private boolean inputAccess;
//    private boolean personalizedAccess;
    private ArrayList<String> personalizedMatkul;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.personalizedMatkul = new ArrayList<>();
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

//    public void setInputAccess(boolean inputAccess) {
//        this.inputAccess = inputAccess;
//    }
//
//    public void setPersonalizedAccess(boolean personalizedAccess) {
//        this.personalizedAccess = personalizedAccess;
//    }
}
