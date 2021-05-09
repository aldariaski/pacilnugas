package com.pacilnugas.authentication.repository;

import com.pacilnugas.authentication.core.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList;

    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User findUser(String username, String password) {
        for (User user: userList) {
            if ((user.getUsername().equalsIgnoreCase(username)) && (user.getPassword().equalsIgnoreCase(password))) {
                return user;
            }
        }
        return  null;
    }
}
