package com.pacilnugas.account.core;

import javax.persistence.Entity;

@Entity
public class Student extends Account {

    public Student() {
        super();
    }

    /**
     * Constructor for a student account with access to personal page.
     */
    public Student(String username, String password) {
        super(username, password);
        this.setType("Student");
        this.setCourseAccess(false);
        this.setPersonalizedAccess(true);
    }
}
