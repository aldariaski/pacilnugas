package com.pacilnugas.authentication.core;

public class Lecturer extends User{

    public Lecturer(String username, String password) {
        super(username, password);
        this.type = "Lecturer";
        this.inputAccess = true;
        this.personalizedAccess = false;
    }
}
