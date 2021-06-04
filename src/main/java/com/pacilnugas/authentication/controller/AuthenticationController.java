package com.pacilnugas.authentication.controller;

import com.pacilnugas.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping("/accountList")
    public String getUserList(Model model){
        model.addAttribute("UserDisplayList", userService.getAllDisplayMessage());
        return "authentication/accountList";
    }

    @GetMapping("/createAccountMenu")
    public String createAccountMenu(Model model) {
        return "authentication/createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        userService.createUser(username, password, type);
        return "redirect:/accountList";
    }

    @GetMapping("/loginMenu")
    public String loginMenu(Model model){
        model.addAttribute("loginStatus", userService.getLoginStatus());
        return "authentication/loginPage";
    }

    @PostMapping("/loginAccount")
    public String loginAccount(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        userService.login(username, password);
        return "redirect:/loginMenu";
    }

    @PostMapping("/logoutAccount")
    public String logoutAccount(HttpServletRequest request){
        userService.logout();
        return "redirect:/loginMenu";
    }
}
