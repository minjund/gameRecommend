package com.minjun.gamerecommend.api.recommend;

import com.minjun.gamerecommend.service.recommend.query.RecommendGameResult;
import com.minjun.gamerecommend.service.recommend.query.RecommendDetailCondition;
import com.minjun.gamerecommend.service.recommend.query.RecommendSearchService;
import com.minjun.gamerecommend.service.recommend.query.GameDetailResult;
import com.minjun.gamerecommend.service.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendApi {

    private final RecommendSearchService recommendSearchService;

    @GetMapping
    public ResponseEntity<RecommendGameResponse> gameRecommendList(@RequestParam String userId){
        UserId loginUserId = UserId.from(userId);

        RecommendGameResult recommendGameResultList = recommendSearchService.findGameList(loginUserId);

        return ResponseEntity.ok(RecommendGameResponse.of(recommendGameResultList));
    }

    @GetMapping("/{appId}")
    public ResponseEntity<?>  RecommendGameDetail(@PathVariable String appId){
        //TODO 상세보기 작업 필요
        RecommendDetailCondition recommendDetailCondition = RecommendDetailCondition.from(appId);

        GameDetailResult gameDetailResult = recommendSearchService.findGameDetail();

        return ResponseEntity.ok().build();
    }
}