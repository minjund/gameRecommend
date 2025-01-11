package com.minjun.gamerecommend.service.game.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameTagMatch {

    public List<List<String>> matchTagIdList(List<String> highestTag, List<String> lowestTag, GameTagParam tagList) {
        List<List<String>> result = new ArrayList<>();
        List<String> highestTagList = new ArrayList<>();
        List<String> lowestTagList = new ArrayList<>();

        for (HashMap<String, String> tag : tagList.tags()) {
            String name = tag.get("name");
            if (highestTag.contains(name)) {
                // tagid를 String에서 Integer로 변환
                highestTagList.add(tag.get("tagid"));
            }

            if (lowestTag.contains(name)) {
                // tagid를 String에서 Integer로 변환
                lowestTagList.add(tag.get("tagid"));
            }
        }

        System.out.println("GameTagMatch:"+lowestTagList);
        System.out.println("GameTagMatch:"+highestTagList);
        result.add(highestTagList);
        result.add(lowestTagList);
        return result;

    }

}
