package com.minjun.gamerecommend.service.score.process;

import com.minjun.gamerecommend.domain.tag.GameTags;
import com.minjun.gamerecommend.service.game.process.ScoreCalculationTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoreCalculation {

    public ScoreCalculationTags ScoreTagId(List<GameTags> gameTags) {
        HashMap<String, Integer> tagCountMap = new HashMap<>();

        for (GameTags info : gameTags) {
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

        return null;
    }
}
