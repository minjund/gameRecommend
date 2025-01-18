package com.minjun.gamerecommend.application.reivew.dto;

import com.minjun.gamerecommend.service.review.ReviewInfo;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record ReviewResponse(ArrayList<String> arrayResponse) {
    public static ReviewResponse from(List<ReviewInfo> reviewList) {
        ReviewResponse response = new ReviewResponse(new ArrayList<>());

        reviewList.forEach(rl -> {
            response.arrayResponse().add(rl.review());
        });

        return response;
    }
}
