package com.minjun.gamerecommend.domain.tag;

import com.minjun.gamerecommend.domain.game.SteamGameExternal;
import com.minjun.gamerecommend.service.recommend.query.dto.GameTagResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagFinder {

    private final SteamGameExternal steamGameExternal;

    public GameTagResult findTagList() {
        GameTag gameTagListResponse = steamGameExternal.callTagList();
        return GameTagResult.from(gameTagListResponse.tags());
    }
}
