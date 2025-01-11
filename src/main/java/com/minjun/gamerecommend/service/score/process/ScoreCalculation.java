package com.minjun.gamerecommend.service.score.process;

import com.minjun.gamerecommend.domain.score.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoreCalculation {

    public HashMap<String, Integer> ScoreTagId(List<ScoreCalculationTagInfo> scoreCalculationTagInfoList) {
        HashMap<String, Integer> tagCountMap = new HashMap<>();

        // scoreCalculationTagInfoList의 각 항목을 순회
        for (ScoreCalculationTagInfo info : scoreCalculationTagInfoList) {
            LinkedHashMap<String, Integer> tagList = info.tags();

            for (Map.Entry<String, Integer> entry : tagList.entrySet()) {
                String tag = entry.getKey();

                // 이미 Map에 존재하는 태그라면 카운트 증가, 없다면 1로 초기화
                if (tagCountMap.containsKey(tag)) {
                    tagCountMap.put(tag, tagCountMap.get(tag) + 1);
                } else {
                    tagCountMap.put(tag, 1);
                }
            }
        }
        System.out.println("tagCountMap: " + tagCountMap);

        // 최종 점수 반환
        return tagCountMap;
    }
}
