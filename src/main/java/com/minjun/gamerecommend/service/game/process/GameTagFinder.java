package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.global.infra.SteamApiCaller;
import com.minjun.gamerecommend.domain.game.GameTagList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagFinder {

    private final SteamApiCaller steamApiCaller;

    public GameTagParam findTagList() {
        GameTagList gameTagList = steamApiCaller.callTagList();
        return GameTagParam.from(gameTagList.tags());
    }
}
