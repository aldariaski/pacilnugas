package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.service.MatkulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.LocalTime;

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
        LocalDate mulai = LocalDate.parse(request.getParameter("tahun"));
        String tahun = request.getParameter("tahun").substring(0, 4);
        int tahunInt = Integer.parseInt(tahun);
        String major = request.getParameter("major");
        int angkatan = Integer.parseInt(request.getParameter("angkatan"));

        matkulService.createMatkul(title, description, mulai, tahunInt, semester, major, angkatan);

        return "redirect:/matkul/all";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/input-matkul")
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

    @RequestMapping(method = RequestMethod.GET, value = "/view/{idAss}")
    public String getSpecificMatkul(Model model, @PathVariable(value = "idAss") int idAss) {
        model.addAttribute("AssignmentIni", matkulService.getMatkulById(idAss));
        return "activities/rincian/rincian";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{idAss}/edit")
    public String getMatkulToEdit(@PathVariable(value = "idAss") int idAss, Model model) {
        model.addAttribute("AssignmentIni", matkulService.getMatkulById(idAss));
        return "activities/edit/editAssignment";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/view/{idAss}/update")
    public String matkulEditForm(
            HttpServletRequest request, @PathVariable(value = "idAss") int idAss, Model model) {
        model.addAttribute("AssignmentIni", matkulService.getMatkulById(idAss));
        String title = request.getParameter("title");
        String matkul = request.getParameter("matkul");
        String description = request.getParameter("description");
        LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
        LocalTime deadlineTime = LocalTime.parse(request.getParameter("deadline-time"));
        matkulService.updateMatkul(idAss, title, description,
                matkul, deadline, deadlineTime);
        return "redirect:/task/view/{idAss}";
    }
}
