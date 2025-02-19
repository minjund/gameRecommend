package com.minjun.gamerecommend.domain.count;

public record TotalCount(Integer count) {
    public TotalCount {
        if(count <= 0){
            throw new IllegalArgumentException("게임을 못 찾았습니다.");
        }
    }

    public static TotalCount of(Integer count) {
        return new TotalCount(count);
    }
}
