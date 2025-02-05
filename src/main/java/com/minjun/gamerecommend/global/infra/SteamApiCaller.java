package com.minjun.gamerecommend.global.infra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.domain.user.UserResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class SteamApiCaller implements SteamGameExternal {
    private static final String steamApiKey = "489DB338ED4D87FA560F11BC5B4B5986";

    public record RecentlyPlayGameResult(@JsonProperty("response") RecentlyPlayGame response) { }
    public record GameTagListResult(@JsonProperty("response") GameTagList response) { }
    public record RecommendGameResult(@JsonProperty("response") RecommendGame response) { }
    public record LoginUserResult(@JsonProperty("response") UserResult response) { }

    @Override
    public UserResult callSteamLoginDetail(String userId) {
        RestClient restClient = buildSteamApiUrl(SteamApiType.LOGIN_DETAIL);

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


    @Override
    public RecentlyPlayGame callRecentlyPlayedGameByUserId(String userId) {
        RestClient restClient = buildSteamApiUrl(SteamApiType.RECENTLY_PLAY_GAME);

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

    @Override
    public GameDetailToTag callGameDetailToTagByAppId(String appId){
        RestClient restClient = buildSteamApiUrl(SteamApiType.GAME_TO_TAG);

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


    @Override
    public GameTagList callTagList() {
        RestClient restClient = buildSteamApiUrl(SteamApiType.MOST_POPULAR_TAGS);

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

    @Override
    public RecommendGame callGameListByTag(String gameRecommendCommandToString){
        RestClient restClient = buildSteamApiUrl(SteamApiType.TAG_TO_GAME);

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

    @Override
    public GameDetail callGameDetailByAppId(Integer appId) {
        RestClient restClient = buildSteamApiUrl(SteamApiType.GAME_DETAIL);
        Map resultGameDetail = Optional.of(Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("appids", appId)
                        .queryParam("l", "korean")
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(Map.class).getBody()).orElseGet(HashMap::new)).orElseGet(HashMap::new);

        return GameDetail.from(resultGameDetail);
    }

    @Override
    public Map callGameDetailReview(String appId){
        RestClient restClient = buildSteamApiUrl(SteamApiType.GAME_REVIEW);
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

    private RestClient buildSteamApiUrl(SteamApiType steamApiType) {
        return RestClient.builder()
                .baseUrl(steamApiType.getUrl())
                .build();
    }
}
