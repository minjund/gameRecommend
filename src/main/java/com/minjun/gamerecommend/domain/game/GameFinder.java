package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.tag.GameDetailToTag;
import com.minjun.gamerecommend.domain.tag.RecommendGameTag;
import com.minjun.gamerecommend.service.recommend.query.dto.GameRecommendCondition;
import com.minjun.gamerecommend.domain.tag.RecommendGameTags;
import com.minjun.gamerecommend.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.minjun.gamerecommend.global.util.ObjectToJson.convert;

@Service
@RequiredArgsConstructor
public class GameFinder {
    private final SteamGameExternal steamGameExternal;

    public Game findUserGame(UserId userId) {
        HaveGame gameHave = findGameHave(userId);
        RecentlyGame recentlyGame = findGameRecentlyPlay(userId);

        return recentlyGame.game();
    }

    // fixme : 8초 걸려요..
    public RecommendGameTags findGameDetailToTagByAppId(Game games) {
        List<RecommendGameTag> recommendGameTags = new ArrayList<>();

        games.findIds().forEach(game -> {
            GameDetailToTag gameDetailToTag = steamGameExternal.callGameDetailToTagByAppId(game.toString());
            recommendGameTags.add(RecommendGameTag.of(gameDetailToTag.appId(), gameDetailToTag.tags()));
        });

        return RecommendGameTags.of(recommendGameTags);
    }

    public RecommendGame findGameListTagFilter(GameRecommendCondition gameRecommendCondition) {
        String gameRecommendCommandToString = convert(gameRecommendCondition);
        return steamGameExternal.callGameListByTag(gameRecommendCommandToString);
    }

    private HaveGame findGameHave(UserId userId) {
        return steamGameExternal.callHaveGameList(userId);
    }

    private RecentlyGame findGameRecentlyPlay(UserId userId) {
        return steamGameExternal.callRecentlyPlayedGameByUserId(userId.value());
    }
}
