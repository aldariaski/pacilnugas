package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/task")
public class MatkulController {
    @Autowired
    private AssignmentService assignmentService;


}
