package com.minjun.gamerecommend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.domain.user.UserResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class SteamApiCaller {
    private static final String steamApiKey = "B13B650A477950A52E089600C57EB17C";

    public record RecentlyPlayGameResult(@JsonProperty("response") RecentlyPlayGame response) { }
    public record GameTagListResult(@JsonProperty("response") GameTagList response) { }
    public record RecommendGameResult(@JsonProperty("response") RecommendGame response) { }
    public record LoginUserResult(@JsonProperty("response") UserResult response) { }

    public UserResult callSteamLoginDetail(String userId) {
        String steamApiUrl= "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/";

        RestClient restClient = buildSteamApiUrl(steamApiUrl);

        return Optional.ofNullable(restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/")
                                .queryParam("key", steamApiKey)
                                .queryParam("steamids", userId)
                                .build())
                        .header("Content-Type", "application/json")
                        .retrieve()
                        .toEntity(LoginUserResult.class).getBody())
                .orElseGet(() -> new LoginUserResult(new UserResult(new ArrayList<>())))
                .response();
    }


    public RecentlyPlayGame callRecentlyPlayedGameByUserId(String userId) {
        String steamApiUrl= "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1";

        RestClient restClient = buildSteamApiUrl(steamApiUrl);


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

    // 게임 태그 조회
    public GameDetailToTag callGameDetailToTagByAppId(String appId){
        String steamApiUrl= "https://steamspy.com/api.php";

        RestClient restClient = buildSteamApiUrl(steamApiUrl);

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("request", "appdetails")
                        .queryParam("appid", appId)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(GameDetailToTag.class).getBody();
    }


    public GameTagList callTagList() {
        String steamApiUrl= "https://api.steampowered.com/IStoreService/GetMostPopularTags/v1";

        RestClient restClient = buildSteamApiUrl(steamApiUrl);

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

    public RecommendGame callGameListByTag(String gameRecommendCommandToString){
        String steamApiUrl= "https://api.steampowered.com";
        RestClient restClient = buildSteamApiUrl(steamApiUrl);

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

    public GameDetail callGameDetailByAppId(Integer appId) {
        String steamApiUrl= "https://store.steampowered.com/api/appdetails";

        RestClient restClient = buildSteamApiUrl(steamApiUrl);
        Map appids = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("appids", appId)
                        .queryParam("l", "korean")
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(Map.class).getBody();

        return GameDetail.from(appids);
    }

    public Map callGameDetailReview(String appId){
        String steamApiUrl= "https://store.steampowered.com/appreviews/";

        RestClient restClient = buildSteamApiUrl(steamApiUrl);
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/"+appId)
                        .queryParam("json", "1")
                        .queryParam("filter", "all")
                        .queryParam("language", "koreana")
                        .queryParam("purchase_type", "all")
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(Map.class).getBody();
    }

    private RestClient buildSteamApiUrl(String steamApiUrl) {
        return RestClient.builder()
                .baseUrl(steamApiUrl)
                .build();
    }
}
