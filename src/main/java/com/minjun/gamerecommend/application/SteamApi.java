package com.minjun.gamerecommend.application;

import com.minjun.gamerecommend.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/steam")
public class SteamApi {

    private final GameService gameService;

    @GetMapping("/login")
    public ResponseEntity<String> steamLoginForm(){
        return gameService.callSteamLoginApi();
    }

    @GetMapping("/login-callback")
    public void steamCallBackInfo(@RequestParam(value = "openid.claimed_id") String openIdClaimedId,
                                  @RequestParam(value = "openid.identity") String identity,
                                  @RequestParam(value = "openid.return_to") String returnTo,
                                  @RequestParam(value = "openid.assoc_handle") String assocHandel,
                                  @RequestParam(value = "openid.signed") String signed,
                                  @RequestParam(value = "openid.sig") String sig){
        System.out.println(openIdClaimedId);
        System.out.println(returnTo);
        System.out.println(assocHandel);
        System.out.println(signed);
        System.out.println(sig);
        System.out.println(identity);
    }

    @GetMapping("/recently-played-games")
    public void RecentlyPlayedGame(@RequestParam String userId){
//내꺼
//        String testUserId = "76561198191923705";
        // 모나와 테스트 계정
        String testUserId = "76561198100860589";

        gameService.findRecentlyPlayedGameByUserId(testUserId);

    }
}