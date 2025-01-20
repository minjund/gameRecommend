package com.minjun.gamerecommend.service.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record ReviewInfo(HashMap<String, Object> review) {
    public static List<ReviewInfo> from(List<HashMap<String, Object>> reviewList) {
        List<ReviewInfo> reviewInfoArrayList = new ArrayList<>();

        reviewList.forEach(review -> {
            reviewInfoArrayList.add(new ReviewInfo(review));
        });

        return reviewInfoArrayList;
    }
}
