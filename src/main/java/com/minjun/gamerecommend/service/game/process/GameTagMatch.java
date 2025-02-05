package com.minjun.gamerecommend.service.game.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.minjun.gamerecommend.global.util.Extractor.extractMaxValues;
import static com.minjun.gamerecommend.global.util.Extractor.extractMinValues;

@Service
@RequiredArgsConstructor
public class GameTagMatch {

    public List<List<String>> matchTagIdList(ScoreCalculationTags scoreTagInfo, GameTagResult tagList) {
        List<String> highestTag = extractMaxValues(scoreTagInfo.tag());
        List<String> lowestTag = extractMinValues(scoreTagInfo.tag());

        List<List<String>> result = new ArrayList<>();
        List<String> highestTagList = new ArrayList<>();
        List<String> lowestTagList = new ArrayList<>();

        for (HashMap<String, Integer> tag : tagList.tags()) {
            String name = tag.get("name");
            if (highestTag.contains(name)) {
                highestTagList.add(tag.get("tagid"));
            }

            if (lowestTag.contains(name)) {
                lowestTagList.add(tag.get("tagid"));
            }
        }

        result.add(highestTagList);
        result.add(lowestTagList);

        return result;
    }

}
