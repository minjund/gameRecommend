package com.minjun.gamerecommend.application.recommend;

import com.minjun.gamerecommend.service.game.RecommendService;
import com.minjun.gamerecommend.service.game.process.GameDetailInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/steam/recommend")
public class RecommendApi {

    private final RecommendService recommendService;

    @GetMapping("/recently-game")
    public ResponseEntity<List<GameDetailInfo>> RecentlyPlayedGame(@RequestParam String userId){
        //내꺼
        String testUserId = "76561198191923705";

        List<GameDetailInfo> recentlyPlayedGameByUserId = recommendService.findRecentlyPlayedGameByUserId(testUserId);
        // 모나와 테스트 계정
//        String testUserId = "76561198100860589";

        return ResponseEntity.ok(recentlyPlayedGameByUserId);
    }
}