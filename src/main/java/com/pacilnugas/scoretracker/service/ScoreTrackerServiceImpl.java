package com.pacilnugas.scoretracker.service;

import com.pacilnugas.scoretracker.core.ScoreDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreTrackerServiceImpl implements ScoreTrackerService {
    @Override
    public ScoreDetail getScore(List<Integer> scoreList, List<Integer> weightList) {
        return new ScoreDetail(scoreList, weightList);
    }
}
