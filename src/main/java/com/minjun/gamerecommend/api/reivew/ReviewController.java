package com.minjun.gamerecommend.api.reivew;

import com.minjun.gamerecommend.api.reivew.dto.ReviewResponse;
import com.minjun.gamerecommend.service.review.query.ReviewInfo;
import com.minjun.gamerecommend.service.review.query.ReviewSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewSearchService reviewSearchService;

    @GetMapping
    public ResponseEntity<ReviewResponse> findReview(@RequestParam String appId){

        List<ReviewInfo> reviewList = reviewSearchService.findReviewList(appId);

        return ResponseEntity.ok(ReviewResponse.from(reviewList));
    }
}
