package com.pacilnugas.authentication.service;

import com.pacilnugas.authentication.core.Lecturer;
import com.pacilnugas.authentication.core.Student;
import com.pacilnugas.authentication.core.TeachingAssistant;
import com.pacilnugas.authentication.core.User;
import com.pacilnugas.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private User activeUser;
    private boolean loginTrial;

    public UserService() {
        this.activeUser = null;
        this.loginTrial = false;
    }

    public void createUser(String username, String password, String type) {
        User newUser = createUserType(username, password, type);
        userRepository.save(newUser);
    }

    public User createUserType(String username, String password, String type) {
        if (type.equalsIgnoreCase("Student")) {
            return new Student(username, password);
        } else if (type.equalsIgnoreCase("Teaching Assistant")) {
            return new TeachingAssistant(username, password);
        } else if (type.equalsIgnoreCase("Lecturer")) {
            return new Lecturer(username, password);
        }
        return  null;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<String> getAllDisplayMessage() {
        List<User> listOfUser = getAllUser();
        List<String> listOfDisplayMessage = new ArrayList<>();
        for (User user: listOfUser) {
            listOfDisplayMessage.add(displayUser(user));
        }
        return listOfDisplayMessage;
    }

    public String displayUser(User user) {
        return user.getType() + " with username " + user.getUsername();
    }

    public User getUser(String username, String password) {
        User foundUser = userRepository.findByUsername(username);
        if (foundUser.getPassword().equalsIgnoreCase(password)) {
            return foundUser;
        } else {
            return null;
        }
    }

    public void login(String username, String password) {
        this.loginTrial = true;
        this.activeUser = getUser(username, password);
    }

    public String getLoginStatus() {
        if (!this.loginTrial) {
            return "";
        }
        else if (this.activeUser == null) {
            return "Login failed";
        } else {
            return loginMessage();
        }
    }

    public String loginMessage() {
        String type = this.activeUser.getType().toLowerCase();
        String username = this.activeUser.getUsername();
        return "You logged in as a " + type + " with username " + username;
    }

    public void logout() {
        this.activeUser = null;
        this.loginTrial = false;
    }
}
