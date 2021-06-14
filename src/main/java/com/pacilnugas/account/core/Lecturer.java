package com.pacilnugas.account.core;

import javax.persistence.Entity;

@Entity
public class Lecturer extends Account {

    public Lecturer() {
        super();
    }

    public Lecturer(String username, String password) {
        super(username, password);
        this.setType("Lecturer");
        this.setCourseAccess(true);
        this.setPersonalizedAccess(false);
    }
}
