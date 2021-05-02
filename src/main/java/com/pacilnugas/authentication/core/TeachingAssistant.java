package com.pacilnugas.authentication.core;

public class TeachingAssistant extends User{

    public TeachingAssistant(String username, String password) {
        super(username, password);
        this.inputAccess = true;
        this.personalizedAccess = true;
    }

    public String display() {
        return "Teaching Assistant with username " + this.username;
    }
}
