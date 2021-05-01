package com.pacilnugas.personalized.controller;

import com.pacilnugas.personalized.core.Task;
import com.pacilnugas.personalized.service.ObserverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/personalized")
public class PersonalizedController {

    @Autowired
    private ObserverServiceImpl observerService;

    @RequestMapping(path = "/create-task", method = RequestMethod.GET)
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "personalized/taskForm";
    }

    @RequestMapping(path = "/add-task", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("task") Task task) {
        observerService.addTask(task);
        return "redirect:/course-list";
    }

    @RequestMapping(value = "/course-list", method = RequestMethod.GET)
    public String getCourses(Model model) {
        model.addAttribute("courses", observerService.getCourses());
        return "personalized/courseList";
    }
}
