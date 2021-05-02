package com.pacilnugas.authentication.service;

import com.pacilnugas.authentication.core.Lecturer;
import com.pacilnugas.authentication.core.Student;
import com.pacilnugas.authentication.core.TeachingAssistant;
import com.pacilnugas.authentication.core.User;
import com.pacilnugas.authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    private UserRepository userRepository;
    private User activeUser;
    private boolean tried;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = new UserRepository();
        this.activeUser = null;
        this.tried = false;
    }

    public void createUser(String username, String password, String type) {
        User newUser = null;
        if (type.equalsIgnoreCase("Student")) {
            newUser = new Student(username, password);
        } else if (type.equalsIgnoreCase("Teaching Assistant")) {
            newUser = new TeachingAssistant(username, password);
        } else if (type.equalsIgnoreCase("Lecturer")) {
            newUser = new Lecturer(username, password);
        }
        userRepository.addUser(newUser);
    }

    public List<User> getUsers() {
        return userRepository.getUserList();
    }

    public User getUser(String username, String password) {
        return userRepository.findUser(username, password);
    }

    public void logIn(String username, String password) {
        tried = true;
        User user = getUser(username, password);
        if (user != null) {
            activeUser = user;
        } else {
            activeUser = null;
        }
    }

    public String getLoggedIn() {
        if (tried == false) {
            return "";
        }
        else if (activeUser == null) {
            return "Login failed";
        } else {
            return activeUser.loginMessage();
        }
    }

    public void logOut() {
        activeUser = null;
        tried = false;
    }
}
