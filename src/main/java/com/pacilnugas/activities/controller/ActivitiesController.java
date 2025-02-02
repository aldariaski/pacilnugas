package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.service.AssignmentService;
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
import java.util.List;

@Controller
@RequestMapping(path = "/task")
public class ActivitiesController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private MatkulService matkulService;

    @RequestMapping(method = RequestMethod.POST, value = "/proses-input-tugas")
    public String assignmentFormPro(HttpServletRequest request) {
        String title = request.getParameter("title");
        String matkul = request.getParameter("matkul");
        String description = request.getParameter("description");
        LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
        LocalTime deadlineTime = LocalTime.parse(request.getParameter("deadline-time"));
        String tahunajaran = request.getParameter("deadline").substring(0, 4);

        assignmentService.createAssignment(title, description,
                matkul, tahunajaran, deadline, deadlineTime);

        return "redirect:/task/all";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/input-tugas")
    public String assignmentForm(Model model) {
        List allMatkul = matkulService.getAllMatkulObject();
        model.addAttribute("listMatkul", allMatkul);
        return "activities/assignment/inputPage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getPersonalizeAssignment(Model model) {
        model.addAttribute("SemuaAssignment", assignmentService.getAllAssignment());
        return "activities/assignment/allAssignment";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public String getAllAssignment(Model model) {
        model.addAttribute("SemuaAssignment", assignmentService.getAllAssignment());
        return "activities/assignment/allAssignment";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{idAss}")
    public String getSpecificAssignment(Model model, @PathVariable(value = "idAss") int idAss) {
        model.addAttribute("AssignmentIni", assignmentService.getAssignmentById(idAss));
        return "activities/rincian/rincian";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{idAss}/edit")
    public String getAssignmentToEdit(@PathVariable(value = "idAss") int idAss, Model model) {
        model.addAttribute("AssignmentIni", assignmentService.getAssignmentById(idAss));
        return "activities/edit/editAssignment";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/view/{idAss}/update")
    public String assignmentEditForm(
            HttpServletRequest request, @PathVariable(value = "idAss") int idAss, Model model) {
        model.addAttribute("AssignmentIni", assignmentService.getAssignmentById(idAss));
        String title = request.getParameter("title");
        String matkul = request.getParameter("matkul");
        String description = request.getParameter("description");
        LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
        LocalTime deadlineTime = LocalTime.parse(request.getParameter("deadline-time"));
        assignmentService.updateAssignment(idAss, title, description,
                matkul, deadline, deadlineTime);
        return "redirect:/task/view/{idAss}";
    }
}
