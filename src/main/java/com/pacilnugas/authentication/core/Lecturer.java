package com.pacilnugas.authentication.core;

public class Lecturer extends User{

    public Lecturer(String username, String password) {
        super(username, password);
        this.type = "Lecturer";
        this.inputAccess = false;
        this.personalizedAccess = true;
    }
}
