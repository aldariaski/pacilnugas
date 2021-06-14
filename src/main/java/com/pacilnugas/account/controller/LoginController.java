package com.pacilnugas.account.controller;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/loginPersonal")
    public String loginPersonal(Model model){
        model.addAttribute("display", "Masukkan akun personal anda yang telah terdaftar.");
        return "account/login/loginPersonalPage";
    }

    @GetMapping("/loginPersonalFail")
    public String loginPersonalFail(Model model){
        model.addAttribute("display", "Tipe akun anda tidak memiliki akses untuk fitur ini.");
        return "account/login/loginPersonalPage";
    }

    @GetMapping("/loginPersonalError")
    public String loginPersonalError(Model model){
        model.addAttribute("display", "Username atau password salah.");
        return "account/login/loginPersonalPage";
    }

    @PostMapping("/loginPersonalProcess")
    public String loginPersonalProcess(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account foundAccount = accountService.getAccount(username, password);
        if (foundAccount == null) {
            return "redirect:/loginPersonalError";
        }
        if (!foundAccount.getPersonalizedAccess()) {
            return "redirect:/loginPersonalFail";
        }
        try {
            username = URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        return "redirect:/personal?username=" + username;
    }

    @GetMapping("/loginCourse")
    public String loginCourse(Model model){
        model.addAttribute("display", "Masukkan akun personal anda yang telah terdaftar!");
        return "account/login/loginCoursePage";
    }

    @GetMapping("/loginCourseFail")
    public String loginCourseFail(Model model){
        model.addAttribute("display", "Tipe akun anda tidak memiliki akses untuk fitur ini.");
        return "account/login/loginCoursePage";
    }

    @GetMapping("/loginCourseError")
    public String loginCourseError(Model model){
        model.addAttribute("display", "Username atau password salah.");
        return "account/login/loginPersonalPage";
    }

    @PostMapping("/loginCourseProcess")
    public String loginCourseProcess(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account foundAccount = accountService.getAccount(username, password);
        if (foundAccount == null) {
            return "redirect:/loginCourseError";
        }
        if (!foundAccount.getCourseAccess()) {
            return "redirect:/loginCourseFail";
        }
        try {
            username = URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        return "redirect:/matkul/input-matkul";
    }
}
