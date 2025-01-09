package com.minjun.gamerecommend.service;

import com.minjun.gamerecommend.domain.extractor.ExtractorList;
import com.minjun.gamerecommend.domain.score.ScoreCalculation;
import com.minjun.gamerecommend.domain.score.ScoreCalculationTagInfo;
import com.minjun.gamerecommend.domain.steam.GameDetailParam;
import com.minjun.gamerecommend.domain.steam.SteamApiCaller;
import com.minjun.gamerecommend.domain.steam.RecentlyPlayGameParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SteamService {
    private final ScoreCalculation scoreCalculation;
    private final ExtractorList extractorList;
    private final SteamApiCaller steamApiCaller;

    public ResponseEntity<String> callSteamLoginApi(){
        return steamApiCaller.callSteamLoginForm();
    }

    public void findRecentlyPlayedGameByUserId(String userId){
        RecentlyPlayGameParam recentlyPlayGameParam = steamApiCaller.callRecentlyPlayedGameByUserId(userId);

        // 게임 리스트 상세 정보 가져오기
        List<GameDetailParam> gameDetailParamList = steamApiCaller.callGameDetailByGameList(recentlyPlayGameParam.response().games());

        //tag 추출 해서 List return
        List<GameDetailParam> gameDetailExtractorTagList = extractorList.gameDetailExtractorTagList(gameDetailParamList);

//        List<ScoreCalculationTagInfo> scoreCalculationTagInfoList = gameDetailExtractorTagList.stream()
//                .map(gameDetailParam -> new ScoreCalculationTagInfo(gameDetailParam.tags()))
//                .toList();
        // 게임 리스트 점수 계산
//        int i = scoreCalculation.scoreTagList(scoreCalculationTagInfoList);

        System.out.println();

    }
}
