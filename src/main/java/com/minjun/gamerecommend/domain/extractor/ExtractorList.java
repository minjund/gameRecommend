package com.minjun.gamerecommend.domain.extractor;

import com.minjun.gamerecommend.domain.score.ScoreCalculationTagInfo;
import com.minjun.gamerecommend.domain.steam.GameDetailParam;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtractorList {

    public List<GameDetailParam> gameDetailExtractorTagList(List<GameDetailParam> gameDetailParamList) {
        return gameDetailParamList.stream()
                .filter(gameDetailParam -> gameDetailParam.tags() != null)
                .collect(Collectors.toList());
    }
}
