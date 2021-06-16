package com.pacilnugas.scoretracker.controller;

import com.pacilnugas.scoretracker.core.ScoreDetail;
import com.pacilnugas.scoretracker.service.ScoreTrackerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrackerRestController.class)
class TrackerRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoreTrackerServiceImpl scoreTrackerService;

    private List<Integer> scoreList;
    private List<Integer> weightList;

    @BeforeEach
    public void setUp() {
        scoreList = new ArrayList<>();
        scoreList.add(90);
        scoreList.add(100);
        weightList = new ArrayList<>();
        weightList.add(50);
        weightList.add(50);
    }

    @Test
    void testControllerGetTrackScore() throws Exception {
        when(scoreTrackerService.getScore(scoreList, weightList))
                .thenReturn(new ScoreDetail(scoreList, weightList));

        mvc.perform(get("/scoretracker/track?scoreList=90,100&weightList=50,50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.score").value("95.0"))
                .andExpect(jsonPath("$.grade").value("A"))
                .andExpect(jsonPath("$.weight").value("100"));
    }
}
