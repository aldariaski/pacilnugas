package com.pacilnugas.authentication.controller;

import com.pacilnugas.authentication.core.Account;
import com.pacilnugas.authentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class AuthenticationController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/existingAccount")
    public String existingAccount(Model model){
        model.addAttribute("AccountDisplayList", accountService.getAllDisplayMessage());
        return "authentication/existingAccountPage";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "authentication/registrationPage";
    }

    @PostMapping("/registrationProcess")
    public String registrationProcess(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        accountService.createAccount(username, password, type);
        return "redirect:/existingAccount";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "authentication/loginPage";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account foundAccount = accountService.getAccount(username, password);
        if (foundAccount == null) {
            return "redirect:/login";
        }
        try {
            username = URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        System.out.println(username);
        return "redirect:/personal?username=" + username;
    }
}
