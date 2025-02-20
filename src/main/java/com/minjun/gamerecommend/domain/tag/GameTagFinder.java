package com.minjun.gamerecommend.domain.tag;

import com.minjun.gamerecommend.domain.game.SteamGameExternal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagFinder {

    private final SteamGameExternal steamGameExternal;

    public GameTagsResult findTagList() {
        GameTags gameTagListResponse = steamGameExternal.callTagList();
        return GameTagsResult.from(gameTagListResponse.tags());
    }
}
