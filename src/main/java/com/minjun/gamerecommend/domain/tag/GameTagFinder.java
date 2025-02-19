package com.minjun.gamerecommend.domain.tag;

import com.minjun.gamerecommend.global.infra.SteamApiCaller;
import com.minjun.gamerecommend.domain.game.GameTagList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagFinder {

    private final SteamApiCaller steamApiCaller;

    public GameTagsResult findTagList() {
        GameTagList gameTagList = steamApiCaller.callTagList();
        return GameTagsResult.from(gameTagList.tags());
    }
}
