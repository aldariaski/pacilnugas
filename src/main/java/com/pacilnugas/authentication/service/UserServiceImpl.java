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

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = new UserRepository();
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
}
