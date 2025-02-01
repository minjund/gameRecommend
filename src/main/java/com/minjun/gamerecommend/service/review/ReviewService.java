package com.minjun.gamerecommend.service.review;

import com.minjun.gamerecommend.global.infra.SteamApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final SteamApiCaller steamApiCaller;

    public List<ReviewInfo> findReviewList(String appId){
         Map gameReviewResult = steamApiCaller.callGameDetailReview(appId);

        List<HashMap<String,Object>> reviewList = (List<HashMap<String,Object>>) gameReviewResult.get("reviews");

        return ReviewInfo.from(reviewList);
    }
}
