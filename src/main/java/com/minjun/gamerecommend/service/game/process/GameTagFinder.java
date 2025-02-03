package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.global.infra.SteamApiCaller;
import com.minjun.gamerecommend.domain.game.GameTagList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagFinder {

    private final SteamApiCaller steamApiCaller;

    public GameTagResult findTagList() {
        GameTagList gameTagList = steamApiCaller.callTagList();
        return GameTagResult.from(gameTagList.tags());
    }
}
