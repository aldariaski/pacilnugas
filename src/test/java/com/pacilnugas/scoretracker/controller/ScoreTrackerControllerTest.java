package com.pacilnugas.scoretracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = ScoreTrackerController.class)
class ScoreTrackerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenScoreTrackerUrlIsAccessedItShouldReturnScoreTracker() throws Exception {
        mockMvc.perform(get("/scoretracker"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("scoreTrackerPage"))
                .andExpect(view().name("scoretracker/scoretracker"));
    }
}
