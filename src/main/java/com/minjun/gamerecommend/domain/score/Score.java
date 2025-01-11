package com.minjun.gamerecommend.domain.score;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Score {
    private String targetId;
    private int score = 0;

    public Score(HashMap<String, Integer> scoreInfo) {
        this.targetId = scoreInfo.keySet().iterator().next();
        this.score = scoreInfo.get(targetId);
    }

    public void incrementScore() {
        this.score++;
    }

    public void isMatchedIncrement(String targetId) {
        if (this.targetId.equals(targetId)) {
            incrementScore();
        } else {
            score = 1;
        }
    }
}
