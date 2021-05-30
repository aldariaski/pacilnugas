package com.pacilnugas.scoretracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/scoretracker")
public class ScoreTrackerController {
    @GetMapping
    public String scoreTrackerPage() {
        return "scoretracker/scoretracker";
    }
}
