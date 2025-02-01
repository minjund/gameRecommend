package com.minjun.gamerecommend.application.recommend;

import com.minjun.gamerecommend.service.recommend.RecommendRecentlyInfo;
import com.minjun.gamerecommend.service.recommend.RecommendService;
import com.minjun.gamerecommend.service.game.process.GameDetailInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/steam/recommend")
public class RecommendApi {

    private final RecommendService recommendService;

    @GetMapping("/recently-game")
    public ResponseEntity<List<GameDetailInfo>> RecentlyPlayedGame(@RequestParam String userId){
        RecommendRecentlyInfo recommendRecentlyInfo = RecommendRecentlyInfo.from(userId);

        List<GameDetailInfo> recentlyPlayedGameByUserId = recommendService.findRecentlyPlayedGameByUserId(recommendRecentlyInfo);

        return ResponseEntity.ok(recentlyPlayedGameByUserId);
    }

    @GetMapping("/detail")
    public ResponseEntity<?>  RecommendDetail(){
        //TODO 상세보기 작업 필요

        return ResponseEntity.ok().build();
    }
}