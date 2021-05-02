package com.pacilnugas.authentication.core;

public abstract class User {
    protected String username;
    protected String password;
    protected boolean inputAccess;
    protected boolean personalizedAccess;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getInputAccess() {
        return inputAccess;
    }

    public boolean getPersonalizedAccess() {
        return personalizedAccess;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInputAccess(boolean inputAccess) {
        this.inputAccess = inputAccess;
    }

    public void setPersonalizedAccess(boolean personalizedAccess) {
        this.personalizedAccess = personalizedAccess;
    }
}
