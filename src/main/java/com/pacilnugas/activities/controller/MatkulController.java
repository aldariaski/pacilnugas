package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.service.MatkulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/matkul")
public class MatkulController {
    @Autowired
    private MatkulService matkulService;

    @RequestMapping(method = RequestMethod.POST, value = "/proses-input-matkul")
    public String matkulFormPro(HttpServletRequest request) {
        String title = request.getParameter("title");
        String semester = request.getParameter("semester");
        String description = request.getParameter("description");
        String tahun = request.getParameter("tahun").substring(0, 4);
        int tahunInt = Integer.parseInt(tahun);

        matkulService.createMatkul(title, description, tahunInt, semester);

        return "redirect:/matkul/all";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/input-tugas")
    public String matkulForm(Model model) {
        return "activities/matkul/inputPageMatkul";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getPersonalizedMatkul(Model model) {
        model.addAttribute("SemuaMatkul", matkulService.getAllMatkul());
        return "activities/matkul/allAssignment";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public String getAllMatkul(Model model) {
        model.addAttribute("SemuaMatkul", matkulService.getAllMatkul());
        return "activities/matkul/allAssignment";
    }
}
