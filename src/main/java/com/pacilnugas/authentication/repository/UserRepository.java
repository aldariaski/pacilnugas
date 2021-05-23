package com.pacilnugas.authentication.repository;

import com.pacilnugas.authentication.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
//    private List<User> userList;
//
//    public UserRepository() {
//        this.userList = new ArrayList<>();
//    }
//
//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void addUser(User user) {
//        userList.add(user);
//    }
//
//    public User findUser(String username, String password) {
//        for (User user: userList) {
//            if ((user.getUsername().equalsIgnoreCase(username)) && (user.getPassword().equalsIgnoreCase(password))) {
//                return user;
//            }
//        }
//        return  null;
//    }
}
