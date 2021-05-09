package com.pacilnugas.authentication.core;

public class Student extends User{

    public Student(String username, String password) {
        super(username, password);
        this.type = "Student";
        this.inputAccess = false;
        this.personalizedAccess = true;
    }
}
