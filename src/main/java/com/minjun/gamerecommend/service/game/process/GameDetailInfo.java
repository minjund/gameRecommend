package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.GameDetail;
import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GameDetailInfo(HashMap<String, Object> gameDetail) {
    public static List<GameDetailInfo> from(List<GameDetail> gameDetailToTagParamList) {
        return gameDetailToTagParamList.stream()
                .map(detail -> {
                            Object gameDetailInfo = detail.gameDetail();
                            HashMap<String, Object> convertedGameDetail = (gameDetailInfo instanceof HashMap) ? (HashMap<String, Object>) gameDetailInfo : new HashMap<>();

                            return GameDetailInfo.builder()
                                    .gameDetail(convertedGameDetail)
                                    .build();
                        }
                )
                .collect(Collectors.toList());

    }
}
