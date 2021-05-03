package com.pacilnugas.landingpage.controller;

import com.pacilnugas.landingpage.model.AssignmentFake;
import com.pacilnugas.landingpage.service.ViewFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/main")
public class LandingPageController {
    @Autowired
    private ViewFilterService viewFilterService;

    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<AssignmentFake>> getListAssignment(
            @RequestParam(defaultValue = "0") int year,
            @RequestParam(defaultValue = "") String major
    ) {
        return ResponseEntity.ok(viewFilterService.getListAssignment(year, major));
    }
}
