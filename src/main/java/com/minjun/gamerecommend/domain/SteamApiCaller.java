package com.minjun.gamerecommend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.GameDetail;
import com.minjun.gamerecommend.domain.game.GameTag;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import com.minjun.gamerecommend.domain.game.RecommendGame;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class SteamApiCaller {
    private static final String steamApiKey = "B13B650A477950A52E089600C57EB17C";
    public record RecentlyPlayGameResult(@JsonProperty("response") RecentlyPlayGame response) { }
    public record GameTagListResult(@JsonProperty("response") GameTag response) { }
    public record RecommendGameResult(@JsonProperty("response") RecommendGame response) { }

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


    public RecentlyPlayGame callRecentlyPlayedGameByUserId(String userId) {
        String steamApiUrl= "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1";

        RestClient restClient = RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();


        return Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("key", steamApiKey)
                        .queryParam("steamid", userId)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(RecentlyPlayGameResult.class).getBody())
                .orElseGet(() -> new RecentlyPlayGameResult(new RecentlyPlayGame(0, new ArrayList<>())))
                .response();
    }

    public GameDetail callGameDetailByAppId(String appId){
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
                .toEntity(GameDetail.class).getBody();
    }


    public GameTag callTagList() {
        String steamApiUrl= "https://api.steampowered.com/IStoreService/GetMostPopularTags/v1";

        RestClient restClient = RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();

        return Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("key", steamApiKey)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(GameTagListResult.class).getBody()).orElseThrow(() -> new RuntimeException("게임 태그가 없습니다."))
                .response();
    }

    public RecommendGame callFiveGameByTag(String gameRecommendCommandToString){
        String steamApiUrl= "https://api.steampowered.com";
        RestClient restClient = RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();

        return Objects.requireNonNull(Optional.of(restClient.get()
                        .uri(uriBuilder ->
                                UriComponentsBuilder
                                        .fromPath("/IStoreQueryService/Query/v1/") // 기본 URI 추가
                                        .queryParam("key", steamApiKey)
                                        .queryParam("input_json", gameRecommendCommandToString)
                                        .build()
                                        .toUri()
                        )
                        .retrieve()
                        .toEntity(RecommendGameResult.class))
                .orElseThrow(() -> new RuntimeException("게임 정보가 없습니다."))
                .getBody()).response();
    }
}
