package com.pacilnugas.authentication.controller;

import com.pacilnugas.authentication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/userList")
    public String getUserList(Model model){
        model.addAttribute("UserList", userService.getUsers());
        return "authentication/userList";
    }

    @PostMapping("/createUser")
    public String createUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        userService.createUser(username, password, type);
        return "redirect:/userList";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loggedInUser", userService.getLoggedIn());
        return "authentication/loginPage";
    }

    @PostMapping("/loggingIn")
    public String loggingIn(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        userService.logIn(username, password);
        return "redirect:/login";
    }

    @PostMapping("/loggingOut")
    public String loggingOut(HttpServletRequest request){
        userService.logOut();
        return "redirect:/login";
    }
}
