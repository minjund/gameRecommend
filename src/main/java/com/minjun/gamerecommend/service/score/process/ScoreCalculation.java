package com.minjun.gamerecommend.service.score.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreCalculation {
    public int scoreTagList(List<ScoreCalculationTagInfo> scoreCalculationTagInfo) {
        scoreCalculationTagInfo.forEach(System.out::println);
            return 0;
    }
}
