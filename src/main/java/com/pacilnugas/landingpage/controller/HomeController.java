package com.pacilnugas.landingpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String pacilnugasHome() {
        return "landingpage/landingpage";
    }
}
