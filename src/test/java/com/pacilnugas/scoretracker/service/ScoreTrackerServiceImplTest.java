package com.pacilnugas.scoretracker.service;

import com.pacilnugas.scoretracker.core.ScoreDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ScoreTrackerServiceImplTest {
    @InjectMocks
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
    void testServiceGetScore() {
        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        ScoreDetail result = scoreTrackerService.getScore(scoreList, weightList);

        Assertions.assertEquals(scoreDetailObject.getScore(), result.getScore());
        Assertions.assertEquals(scoreDetailObject.getGrade(), result.getGrade());
        Assertions.assertEquals(scoreDetailObject.getWeight(), result.getWeight());
    }
}
