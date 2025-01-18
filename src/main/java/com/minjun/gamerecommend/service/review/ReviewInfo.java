package com.minjun.gamerecommend.service.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record ReviewInfo(String review) {
    public static List<ReviewInfo> from(List<HashMap<String, Object>> reviewList) {

        List<ReviewInfo> reviewInfoArrayList = new ArrayList<>();
        reviewList.forEach(review -> {
            String s = String.valueOf(review.get("review"));
            reviewInfoArrayList.add(new ReviewInfo(s));
        });

        return reviewInfoArrayList;
    }
}
