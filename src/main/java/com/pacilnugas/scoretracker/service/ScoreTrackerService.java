package com.pacilnugas.scoretracker.service;

import com.pacilnugas.scoretracker.core.ScoreDetail;

import java.util.List;

public interface ScoreTrackerService {
    ScoreDetail getScore(List<Integer> scoreList, List<Integer> weightList);
}
