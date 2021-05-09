package com.pacilnugas.authentication.core;

public class TeachingAssistant extends User{

    public TeachingAssistant(String username, String password) {
        super(username, password);
        this.type = "Teaching Assistant";
        this.inputAccess = true;
        this.personalizedAccess = true;
    }
}
