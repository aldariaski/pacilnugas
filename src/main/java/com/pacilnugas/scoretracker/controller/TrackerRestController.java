package com.pacilnugas.scoretracker.controller;

import com.pacilnugas.scoretracker.core.ScoreDetail;
import com.pacilnugas.scoretracker.service.ScoreTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/scoretracker")
public class TrackerRestController {
    @Autowired
    private ScoreTrackerService scoreTrackerService;

    @GetMapping(path = "/track")
    public ResponseEntity<ScoreDetail> trackScore(
            @RequestParam List<Integer> scoreList,
            @RequestParam List<Integer> weightList
    ) {
        return ResponseEntity.ok(scoreTrackerService.getScore(scoreList, weightList));
    }
}
