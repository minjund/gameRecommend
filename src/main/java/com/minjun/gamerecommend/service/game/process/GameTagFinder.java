package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.SteamApiCaller;
import com.minjun.gamerecommend.domain.game.GameTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagFinder {

    private final SteamApiCaller steamApiCaller;

    public GameTagParam findTagList() {
        GameTag gameTag = steamApiCaller.callTagList();
        return GameTagParam.from(gameTag.tags());
    }
}
