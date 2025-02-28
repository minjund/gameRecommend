package com.minjun.gamerecommend.infra.external;

import com.minjun.gamerecommend.domain.tag.GameTag;
import com.minjun.gamerecommend.infra.external.dto.*;
import com.minjun.gamerecommend.infra.external.dto.LoginUserResult.UserResponse;
import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.domain.tag.GameDetailToTag;
import com.minjun.gamerecommend.domain.user.UserDetail;
import com.minjun.gamerecommend.domain.user.UserId;
import com.minjun.gamerecommend.infra.external.dto.RecommendGameResult.RecommendGameResponse;
import com.minjun.gamerecommend.service.recommend.query.dto.GameDetailToTagResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class SteamApiCaller implements SteamGameExternal {
    private static final String steamApiKey = "489DB338ED4D87FA560F11BC5B4B5986";

    @Override
    public UserDetail callSteamLoginDetail(String userId) {
        RestClient restClient = buildSteamApiUrl(SteamApiType.LOGIN_DETAIL);

        UserResponse response = Optional.ofNullable(restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/")
                                .queryParam("key", steamApiKey)
                                .queryParam("steamids", userId)
                                .build())
                        .header("Content-Type", "application/json")
                        .retrieve()
                        .toEntity(LoginUserResult.class).getBody())
                .orElseGet(() -> new LoginUserResult(new UserResponse(new ArrayList<>())))
                .response();

        return new UserDetail(response.players());
    }


    @Override
    public RecentlyGame callRecentlyPlayedGameByUserId(String userId) {
        RestClient restClient = buildSteamApiUrl(SteamApiType.RECENTLY_PLAY_GAME);

        RecentlyPlayGameResult.RecentlyPlayGameResponse response = Optional.ofNullable(restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/")
                                .queryParam("key", steamApiKey)
                                .queryParam("steamid", userId)
                                .build())
                        .header("Content-Type", "application/json")
                        .retrieve()
                        .toEntity(RecentlyPlayGameResult.class).getBody())
                .orElseGet(() -> new RecentlyPlayGameResult(new RecentlyPlayGameResult.RecentlyPlayGameResponse(0, new ArrayList<>())))
                .response();

        return RecentlyGame.from(response.totalCount(), response.games());
    }

    @Override
    public GameDetailToTag callGameDetailToTagByAppId(String appId){
        RestClient restClient = buildSteamApiUrl(SteamApiType.GAME_TO_TAG);

        GameDetailToTagResult result = Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("request", "appdetails")
                        .queryParam("appid", appId)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(GameDetailToTagResult.class).getBody())
                .orElseGet(GameDetailToTagResult::empty);

        return GameDetailToTag.of(result.appId(), result.tags());
    }


    @Override
    public GameTag callTagList() {
        RestClient restClient = buildSteamApiUrl(SteamApiType.MOST_POPULAR_TAGS);

        GameTagListResult.GameTagListResponse result = Optional.ofNullable(
                restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/")
                                .queryParam("key", steamApiKey)
                                .build())
                        .header("Content-Type", "application/json")
                        .retrieve()
                        .toEntity(GameTagListResult.class).getBody())
                .orElseThrow(() -> new RuntimeException("게임 태그가 없습니다.")
                ).response();

        return new GameTag(result.tags());
    }

    @Override
    public RecommendGame callGameListByTag(String gameRecommendCommandToString){
        RestClient restClient = buildSteamApiUrl(SteamApiType.TAG_TO_GAME);

        RecommendGameResponse response = Objects.requireNonNull(Optional.of(restClient.get()
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

        return RecommendGame.of(response.gameList());
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

    @Override
    public HaveGame callHaveGameList(UserId userId) {
        RestClient restClient = buildSteamApiUrl(SteamApiType.HAVE_GAME);

        HaveGameResult.HaveGameResponse haveGameResponse = Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("key", steamApiKey)
                        .queryParam("steamid", userId.value())
                        .queryParam("include_appinfo", true)
                        .queryParam("language", "en")
                        .queryParam("include_extended_appinfo", true)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(HaveGameResult.class)
                .getBody().response()
        ).orElseThrow(() -> new RuntimeException("게임 정보가 없습니다."));

        return HaveGame.from(haveGameResponse.totalCount(), haveGameResponse.games());
    }

    private RestClient buildSteamApiUrl(SteamApiType steamApiType) {
        return RestClient.builder()
                .baseUrl(steamApiType.getUrl())
                .build();
    }
}
