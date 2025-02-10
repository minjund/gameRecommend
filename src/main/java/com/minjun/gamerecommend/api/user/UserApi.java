package com.minjun.gamerecommend.api.user;

import com.minjun.gamerecommend.api.user.dto.SteamLoginCallBackRequest;
import com.minjun.gamerecommend.api.user.dto.SteamLoginCallBackResponse;
import com.minjun.gamerecommend.service.user.UserId;
import com.minjun.gamerecommend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApi {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<SteamLoginCallBackResponse> steamCallBackInfo(@RequestBody SteamLoginCallBackRequest steamLoginCallBackRequest) {
        String userId = steamLoginCallBackRequest.openidClaimedId().replace("https://steamcommunity.com/openid/id/", "");
        UserId saveUserId = userService.saveUser(userId);

        return ResponseEntity.ok(SteamLoginCallBackResponse.from(saveUserId));
    }
}
