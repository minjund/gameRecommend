package com.minjun.gamerecommend.domain.steam;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SteamApiCaller {

    String steamApiKey = "B13B650A477950A52E089600C57EB17C";

    public ResponseEntity<String> callSteamLoginForm(){
        String steamApiUrl= "https://steamcommunity.com/openid/login";
        String returnUrl = "http://127.0.0.1:8084/api/v1/steam/login-info";

        RestClient restClient = RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("openid.ns", "http://specs.openid.net/auth/2.0")
                        .queryParam("openid.claimed_id", "http://specs.openid.net/auth/2.0/identifier_select")
                        .queryParam("openid.identity", "http://specs.openid.net/auth/2.0/identifier_select")
                        .queryParam("openid.return_to", returnUrl)
                        .queryParam("openid.realm", "http://127.0.0.1:8084")
                        .queryParam("openid.mode","checkid_setup")
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(String.class);
    }


    public RecentlyPlayGameParam callRecentlyPlayedGameByUserId(String userId) {
        String steamApiUrl= "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1";

        RestClient restClient = RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("key", steamApiKey)
                        .queryParam("steamid", userId)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(RecentlyPlayGameParam.class).getBody();
    }

    public List<GameDetailParam> callGameDetailByGameList(List<HashMap<String, String>> games){
        List<GameDetailParam> gameDetailParamList = new ArrayList<>();

        games.forEach(game -> {
            gameDetailParamList.add(callGameDetailByAppId(game.get("appid")).createGameDetailParam());
        });

        return gameDetailParamList;
    }

    private GameDetailParam callGameDetailByAppId(String appId){
        String steamApiUrl= "https://steamspy.com/api.php";

        RestClient restClient = RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("request", "appdetails")
                        .queryParam("appid", appId)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(GameDetailParam.class).getBody();
    }
}
