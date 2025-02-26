package com.minjun.gamerecommend.domain.score;

public record Score(Integer score) {
    public static Score of(Integer score) {
        return new Score(score);
    }

    public Integer increment() {
        return score + 1;
    }
}
