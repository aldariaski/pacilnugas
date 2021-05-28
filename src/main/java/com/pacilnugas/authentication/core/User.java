package com.pacilnugas.authentication.core;

public abstract class User {
    protected String username;
    protected String password;
    protected String type;
    protected boolean inputAccess;
    protected boolean personalizedAccess;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String displayUser() {
        return this.type + " with username " + this.username;
    }

    public String loginMessage() {
        return "You logged in as a " + this.type.toLowerCase() + " with username " + this.username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}
