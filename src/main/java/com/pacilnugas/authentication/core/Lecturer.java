package com.pacilnugas.authentication.core;

import javax.persistence.Entity;

@Entity
public class Lecturer extends Account {

    public Lecturer() {
        super();
    }

    public Lecturer(String username, String password) {
        super(username, password);
        this.setType("Lecturer");
        this.setInputAccess(true);
        this.setPersonalizedAccess(false);
    }
}
