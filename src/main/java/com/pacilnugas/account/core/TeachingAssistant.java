package com.pacilnugas.account.core;

import javax.persistence.Entity;

@Entity
public class TeachingAssistant extends Account {

    public TeachingAssistant() {
        super();
    }

    /**
     * Constructor for a teaching assistant account with access to course creator and personal page.
     */
    public TeachingAssistant(String username, String password) {
        super(username, password);
        this.setType("Teaching Assistant");
        this.setCourseAccess(true);
        this.setPersonalizedAccess(true);
    }
}
