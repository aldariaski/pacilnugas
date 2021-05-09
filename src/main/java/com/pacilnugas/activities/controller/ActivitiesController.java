package com.pacilnugas.activities.controller;

import org.springframework.stereotype.Controller;
import com.pacilnugas.activities.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/task")
public class ActivitiesController {
    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(method = RequestMethod.POST, value = "/proses-input-tugas")
    public String assignmentFormPro(HttpServletRequest request) {
        String title = request.getParameter("title");
        String matkul = request.getParameter("matkul");
        String description = request.getParameter("description");
        String deadline = request.getParameter("deadline");
        String tahunajaran = request.getParameter("deadline").substring(0, 4);

        assignmentService.createAssignment(title, description,
                matkul, tahunajaran,  deadline);

        return "redirect:/task/all";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/input-tugas")
    public String assignmentForm(Model model) {
        return "activities/inputPage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getPersonalizeAssignment(Model model) {
        model.addAttribute("SemuaAssignment", assignmentService.getAllAssignment());
        return "activities/allAssignment";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public String getAllAssignment(Model model) {
        model.addAttribute("SemuaAssignment", assignmentService.getAllAssignment());
        return "activities/allAssignment";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fakepage")
    public String fakePage(Model model) {
        return "activities/errorLandingPage";
    }

}
