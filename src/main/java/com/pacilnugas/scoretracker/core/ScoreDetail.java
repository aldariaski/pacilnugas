package com.pacilnugas.scoretracker.core;

import java.util.List;

public class ScoreDetail {
    private double score;
    private String grade;
    private int weight;

    public ScoreDetail(List<Integer> scoreList, List<Integer> weightList) {
        score = countScore(scoreList, weightList);
        grade = convertToGrade(score);
    }

    private double countScore(List<Integer> scoreList, List<Integer> weightList) {
        double totalScore = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            weight += weightList.get(i);
            totalScore += scoreList.get(i) * (weightList.get(i) / 100.0);
        }
        return totalScore;
    }

    private String convertToGrade(double score) {
        if (score >= 85) {
            return "A";
        } else if (score >= 80) {
            return "A-";
        } else if (score >= 75) {
            return "B+";
        } else if (score >= 70) {
            return "B";
        } else if (score >= 65) {
            return "B-";
        } else if (score >= 60) {
            return "C+";
        } else if (score >= 55) {
            return "C";
        } else if (score >= 40) {
            return "D";
        } else {
            return "E";
        }
    }

    public double getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public int getWeight() {
        return weight;
    }
}
