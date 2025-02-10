package com.minjun.gamerecommend.service.tag;

import com.minjun.gamerecommend.service.score.process.ScoreCalculationTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameTagMatch {

    //FIXME : 이거 객체로 빼주세요..
    public List<List<String>> matchTagIdList(ScoreCalculationTags scoreTagInfo, GameTagsResult tagList) {

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
