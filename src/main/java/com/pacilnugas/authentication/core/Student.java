package com.pacilnugas.authentication.core;

public class Student extends User{

    public Student(String username, String password) {
        super(username, password);
        this.inputAccess = false;
        this.personalizedAccess = true;
    }

    public String display() {
        return "Student with username " + this.username;
    }

    public String loginMessage() {
        return "You logged in as a student with the username " + username + " and password " + password;
    }
}
