package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/input")
public class InputPageController {
    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(method = RequestMethod.POST, value = "/input")
    public String assignmentForm(HttpServletRequest request) {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String major = request.getParameter("major");
        String tahunajaran = request.getParameter("tahunajaran");

        assignmentService.createAssignment(title, description, major, tahunajaran);

        /*model.addAttribute("title", assignmentService.getRequestValue());
        model.addAttribute("description", assignmentService.getRequestValue());
        model.addAttribute("major", assignmentService.getRequestValue());
        model.addAttribute("tahunajaran", assignmentService.getRequestValue());*/
        return "greeting";
    }


}
