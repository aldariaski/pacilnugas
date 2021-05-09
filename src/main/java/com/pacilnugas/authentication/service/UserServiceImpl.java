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
    private boolean loginTrial;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = new UserRepository();
        this.activeUser = null;
        this.loginTrial = false;
    }

    public void createUser(String username, String password, String type) {
        User newUser = createUserType(username, password, type);
        userRepository.addUser(newUser);
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
        return userRepository.getUserList();
    }

    public User getUser(String username, String password) {
        return userRepository.findUser(username, password);
    }

    public void login(String username, String password) {
        this.loginTrial = true;
        User user = getUser(username, password);
        if (user != null) {
            activeUser = user;
        } else {
            activeUser = null;
        }
    }

    public String getLoginStatus() {
        if (this.loginTrial == false) {
            return "";
        }
        else if (this.activeUser == null) {
            return "Login failed";
        } else {
            return this.activeUser.loginMessage();
        }
    }

    public void logout() {
        this.activeUser = null;
        this.loginTrial = false;
    }
}
