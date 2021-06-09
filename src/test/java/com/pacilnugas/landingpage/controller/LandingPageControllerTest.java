package com.pacilnugas.landingpage.controller;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.landingpage.service.ViewFilterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LandingPageController.class)
class LandingPageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ViewFilterServiceImpl viewFilterService;

    private Assignment assignment1;
    private Assignment assignment2;
    private Assignment assignment3;
    private List<Assignment> filteredAssignmentList;

    @BeforeEach
    public void setUp() {
        assignment1 = new Assignment(
                "Group Project - Adpro", "Ilmu Komputer", 2019, LocalDateTime.now());
        assignment2 = new Assignment(
                "Lab 4 - DDAK", "Sistem Informasi", 2020, LocalDateTime.now());
        assignment3 = new Assignment(
                "Lab 4 - POK", "Ilmu Komputer", 2020, LocalDateTime.now());
        filteredAssignmentList = new ArrayList<>();
    }

    @Test
    void testControllerGetListAssignmentWithoutParameter() throws Exception {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        // No filters
        when(viewFilterService.getListAssignment(0, ""))
                .thenReturn(filteredAssignmentList);

        mvc.perform(get("/main")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].angkatan").value(assignment1.getAngkatan()))
                .andExpect(jsonPath("$[1].angkatan").value(assignment2.getAngkatan()))
                .andExpect(jsonPath("$[2].angkatan").value(assignment3.getAngkatan()));
    }

    @Disabled
    @Test
    void testControllerGetListAssignmentWithYearParameter() throws Exception {
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        // Filter assignment by angkatan = 2020
        when(viewFilterService.getListAssignment(2020, ""))
                .thenReturn(filteredAssignmentList);

        mvc.perform(get("/main?year=2020")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].angkatan").value(2020))
                .andExpect(jsonPath("$[1].angkatan").value(2020));
    }

    @Test
    void testControllerGetListAssignmentWithMajorParameter() throws Exception {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment3);

        // Filter assignment by major = "Ilmu Komputer"
        when(viewFilterService.getListAssignment(0, "Ilmu Komputer"))
                .thenReturn(filteredAssignmentList);

        mvc.perform(get("/main?major=Ilmu Komputer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].major").value("Ilmu Komputer"))
                .andExpect(jsonPath("$[1].major").value("Ilmu Komputer"));
    }

    @Disabled
    @Test
    void testControllerGetListAssignmentWithYearAndMajorParameter() throws Exception {
        filteredAssignmentList.add(assignment2);

        // Filter assignment by year = 2020 and major = "Sistem Informasi"
        when(viewFilterService.getListAssignment(2020, "Sistem Informasi"))
                .thenReturn(filteredAssignmentList);

        mvc.perform(get("/main?year=2020&major=Sistem Informasi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].angkatan").value(2020))
                .andExpect(jsonPath("$[0].major").value("Sistem Informasi"));
    }
}
