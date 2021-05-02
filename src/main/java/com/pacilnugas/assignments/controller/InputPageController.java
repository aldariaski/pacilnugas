package com.pacilnugas.assignments.controller;

import com.pacilnugas.assignments.model.Assignment;

import com.pacilnugas.assignments.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/input")
public class InputPageController {
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/input")
    public String assignmentForm(Model model) {

        model.addAttribute("title", assignmentService.getRequestValue());
        model.addAttribute("description", assignmentService.getRequestValue());
        model.addAttribute("major", assignmentService.getRequestValue());
        model.addAttribute("tahunajaran", assignmentService.getRequestValue());
        return "greeting";
    }
}
