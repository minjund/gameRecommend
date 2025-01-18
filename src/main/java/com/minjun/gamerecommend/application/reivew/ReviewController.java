package com.minjun.gamerecommend.application.reivew;

import com.minjun.gamerecommend.application.reivew.dto.ReviewResponse;
import com.minjun.gamerecommend.service.review.ReviewInfo;
import com.minjun.gamerecommend.service.review.ReviewService;
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

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ReviewResponse> findReview(@RequestParam String appId){

        List<ReviewInfo> reviewList = reviewService.findReviewList(appId);

        return ResponseEntity.ok(ReviewResponse.from(reviewList));
    }
}
