package com.pacilnugas.authentication.core;

import javax.persistence.Entity;

@Entity
public class TeachingAssistant extends Account {

    public TeachingAssistant() {
        super();
    }

    public TeachingAssistant(String username, String password) {
        super(username, password);
        this.setType("Teaching Assistant");
//        this.setInputAccess(true);
//        this.setPersonalizedAccess(true);
    }
}
