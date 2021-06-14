package com.pacilnugas.account.controller;

import com.pacilnugas.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/existingAccount")
    public String existingAccount(Model model){
        model.addAttribute("AccountDisplayList", accountService.getAllDisplayMessage());
        return "account/registration/existingAccountPage";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("display", "");
        return "account/registration/registrationPage";
    }

    @GetMapping("/registrationUsed")
    public String registrationUsed(Model model) {
        model.addAttribute("display", "Username sudah digunakan.");
        return "account/registration/registrationPage";
    }

    @GetMapping("/registrationError")
    public String registrationError(Model model) {
        model.addAttribute("display", "Pastikan anda menulis password dengan benar.");
        return "account/registration/registrationPage";
    }

    @PostMapping("/registrationProcess")
    public String registrationProcess(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (accountService.checkUsernameUsed(username)) {
            return "redirect:/registrationUsed";
        }
        if (!password.equals(confirmPassword)) {
            return "redirect:/registrationError";
        }
        String type = request.getParameter("type");
        accountService.createAccount(username, password, type);
        return "redirect:/existingAccount";
    }
}
