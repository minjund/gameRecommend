package com.minjun.gamerecommend.service.game;

import com.minjun.gamerecommend.service.game.process.*;
import com.minjun.gamerecommend.service.score.process.ScoreCalculationTagInfo;
import com.minjun.gamerecommend.service.score.process.ScoreCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final ScoreCalculation scoreCalculation;
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;
    private final GameTagMatch gameTagMatch;

    public ResponseEntity<String> callSteamLoginApi(){
        return gameFinder.callSteamLoginForm();
    }

    public void findRecentlyPlayedGameByUserId(String userId){
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.findGameRecentlyPlay(userId);

        // FIXME: api 호출 할 때 너무 오래 걸려서 수정 필요
        List<GameDetailInfo> gameDetailInfoList = gameFinder.findGameDetailByAppId(recentlyPlayGameInfo.games());

        List<ScoreCalculationTagInfo> scoreCalculationTagInfoList = gameDetailInfoList.stream()
                .map(gameDetailInfo -> new ScoreCalculationTagInfo(gameDetailInfo.tags()))
                .collect(Collectors.toList());

        HashMap<String, Integer> stringIntegerHashMap = scoreCalculation.ScoreTagId(scoreCalculationTagInfoList);

        // 가장 높은 값과 가장 낮은 값을 가진 HashMap 생성
        List<String> highestTag = extractMaxValues(stringIntegerHashMap);
        List<String> lowestTag = extractMinValues(stringIntegerHashMap);


        // 출력
        System.out.println("가장 높은 값들: " + highestTag); // {D=5, E=5}
        System.out.println("가장 낮은 값들: " + lowestTag); // {F=0}

        GameTagParam tagList = gameTagFinder.findTagList();
        List<List<String>> tagIdList = gameTagMatch.matchTagIdList(highestTag, lowestTag, tagList);

        GameRecommendCommand gameRecommendCommand = GameRecommendCommand.createGameRecommendCommand(tagIdList);
        gameFinder.findFiveGameTagFilter(gameRecommendCommand);
    }

    // 가장 높은 값들 추출
    public static List<String> extractMaxValues(HashMap<String, Integer> inputMap) {
        if (inputMap.isEmpty()) return new ArrayList<>();

        // 최대 값 찾기
        int maxValue = Collections.max(inputMap.values());

        // 최대 값과 같은 항목들을 새로운 HashMap으로 반환
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // 가장 낮은 값들 추출
    public static List<String > extractMinValues(HashMap<String, Integer> inputMap) {
        if (inputMap.isEmpty()) return new ArrayList<>();

        // 최소 값 찾기
        int minValue = Collections.min(inputMap.values());

        // 최소 값과 같은 항목들을 새로운 HashMap으로 반환
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            if (entry.getValue() == minValue) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
