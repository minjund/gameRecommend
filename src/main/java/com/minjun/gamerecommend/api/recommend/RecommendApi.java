package com.minjun.gamerecommend.api.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;
import com.minjun.gamerecommend.service.recommend.RecommendDetailInfo;
import com.minjun.gamerecommend.service.recommend.RecommendGameInfo;
import com.minjun.gamerecommend.service.recommend.RecommendService;
import com.minjun.gamerecommend.service.game.process.GameDetailInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendApi {

    private final RecommendService recommendService;

    @GetMapping
    public ResponseEntity<RecommendGameResponse> gameRecommendList(@RequestParam String userId){
        RecommendGameInfo recommendGameInfo = RecommendGameInfo.from(userId);

        RecommendGame recommendGameList = recommendService.findGameList(recommendGameInfo);

        return ResponseEntity.ok(RecommendGameResponse.of(recommendGameList));
    }

    @GetMapping("/{appId}")
    public ResponseEntity<?>  RecommendGameDetail(@PathVariable String appId){
        //TODO 상세보기 작업 필요
        RecommendDetailInfo recommendDetailInfo = RecommendDetailInfo.from(appId);

        GameDetailInfo gameDetailInfo = recommendService.findGameDetail();

        return ResponseEntity.ok().build();
    }
}