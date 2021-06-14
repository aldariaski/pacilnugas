package com.pacilnugas.authentication.core;

import javax.persistence.Entity;

@Entity
public class Student extends Account {

    public Student() {
        super();
    }

    public Student(String username, String password) {
        super(username, password);
        this.setType("Student");
        this.setInputAccess(false);
        this.setPersonalizedAccess(true);
    }
}
