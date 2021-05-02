package com.pacilnugas.authentication.core;

public class Lecturer extends User{

    public Lecturer(String username, String password) {
        super(username, password);
        this.inputAccess = false;
        this.personalizedAccess = true;
    }

    public String display() {
        return "Lecturer with username " + this.username;
    }
}
