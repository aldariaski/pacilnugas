package com.pacilnugas.account.controller;

import com.pacilnugas.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/loginPersonal")
    public String loginPersonal(Model model) {
        model.addAttribute("display", "Masukkan akun personal anda yang telah terdaftar.");
        return "account/login/loginPersonalPage";
    }

    @GetMapping("/loginPersonalFail")
    public String loginPersonalFail(Model model) {
        model.addAttribute("display", "Tipe akun anda tidak memiliki akses untuk fitur ini.");
        return "account/login/loginPersonalPage";
    }

    @GetMapping("/loginPersonalError")
    public String loginPersonalError(Model model) {
        model.addAttribute("display", "Username atau password salah.");
        return "account/login/loginPersonalPage";
    }

    /**
     * Login account to access personal page.
     */
    @PostMapping("/loginPersonalProcess")
    public String loginPersonalProcess(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String redirect = accountService.authenticatePersonal(username, password);
        return "redirect:/" + redirect;
    }

    @GetMapping("/loginCourse")
    public String loginCourse(Model model) {
        model.addAttribute("display", "Masukkan akun personal anda yang telah terdaftar!");
        return "account/login/loginCoursePage";
    }

    @GetMapping("/loginCourseFail")
    public String loginCourseFail(Model model) {
        model.addAttribute("display", "Tipe akun anda tidak memiliki akses untuk fitur ini.");
        return "account/login/loginCoursePage";
    }

    @GetMapping("/loginCourseError")
    public String loginCourseError(Model model) {
        model.addAttribute("display", "Username atau password salah.");
        return "account/login/loginCoursePage";
    }

    /**
     * Login account to access course creator page.
     */
    @PostMapping("/loginCourseProcess")
    public String loginCourseProcess(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String redirect = accountService.authenticateCourse(username, password);
        return "redirect:/" + redirect;
    }
}
