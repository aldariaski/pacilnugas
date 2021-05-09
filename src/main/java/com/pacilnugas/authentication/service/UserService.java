package com.pacilnugas.authentication.service;

import com.pacilnugas.authentication.core.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void createUser(String username, String password, String type);
    void createUserType(String username, String password, String type);
    List<User> getAllUser();
    User getUser(String username, String password);
    void login(String username, String password);
    String getLoginStatus();
    void logout();
}
