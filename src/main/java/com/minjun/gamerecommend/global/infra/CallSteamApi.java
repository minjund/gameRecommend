package com.minjun.gamerecommend.global.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class CallSteamApi {

    RestClient defaultClient = RestClient.create();

    public String getSteamAppList() {
//        String steamApiUrl = "http://api.steamp∂owered.com/ISteamApps/GetAppList/v0002/";
//
//        RestClient customClient = defaultClient
//                .get()
//                .uri(steamApiUrl)
//                .accept("application/json")
//                .build();

        return "";
    }
}
