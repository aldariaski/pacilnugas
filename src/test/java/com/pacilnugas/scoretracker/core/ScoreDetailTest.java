package com.pacilnugas.scoretracker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ScoreDetailTest {
    private List<Integer> scoreList;
    private List<Integer> weightList;

    @BeforeEach
    public void setUp() {
        scoreList = new ArrayList<>();
        weightList = new ArrayList<>();
    }

    @Test
    void testCreateScoreDetail() {
        scoreList.add(90);
        scoreList.add(100);
        weightList.add(50);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(95, scoreDetailObject.getScore());
        Assertions.assertEquals("A", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeAmin() {
        scoreList.add(100);
        scoreList.add(100);
        weightList.add(30);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(80, scoreDetailObject.getScore());
        Assertions.assertEquals("A-", scoreDetailObject.getGrade());
        Assertions.assertEquals(80, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeBplus() {
        scoreList.add(50);
        scoreList.add(100);
        weightList.add(50);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(75, scoreDetailObject.getScore());
        Assertions.assertEquals("B+", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeB() {
        scoreList.add(100);
        scoreList.add(40);
        weightList.add(50);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(70, scoreDetailObject.getScore());
        Assertions.assertEquals("B", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeBmin() {
        scoreList.add(65);
        weightList.add(100);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(65, scoreDetailObject.getScore());
        Assertions.assertEquals("B-", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeCplus() {
        scoreList.add(120);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(60, scoreDetailObject.getScore());
        Assertions.assertEquals("C+", scoreDetailObject.getGrade());
        Assertions.assertEquals(50, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeC() {
        scoreList.add(55);
        weightList.add(100);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(55, scoreDetailObject.getScore());
        Assertions.assertEquals("C", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeD() {
        scoreList.add(0);
        scoreList.add(80);
        weightList.add(50);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(40, scoreDetailObject.getScore());
        Assertions.assertEquals("D", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }

    @Test
    void testCreateScoreDetailForGradeE() {
        scoreList.add(0);
        scoreList.add(0);
        weightList.add(50);
        weightList.add(50);

        ScoreDetail scoreDetailObject = new ScoreDetail(scoreList, weightList);
        Assertions.assertEquals(0, scoreDetailObject.getScore());
        Assertions.assertEquals("E", scoreDetailObject.getGrade());
        Assertions.assertEquals(100, scoreDetailObject.getWeight());
    }
}
