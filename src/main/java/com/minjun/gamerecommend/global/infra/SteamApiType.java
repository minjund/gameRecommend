package com.minjun.gamerecommend.global.infra;

import lombok.Getter;

@Getter
public enum SteamApiType {

    LOGIN_DETAIL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/","게임 로그인 유저 정보"),
    RECENTLY_PLAY_GAME("http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1","최신 플레이한 게임 api 호출"),
    GAME_TO_TAG("https://steamspy.com/api.php","게임에 들어 가는 태그"),
    MOST_POPULAR_TAGS("http://api.steampowered.com/IStoreService/GetMostPopularTags/v1","스팀 인기 많은 태그 리스트"),
    TAG_TO_GAME("http://api.steampowered.com","태그에 속한 게임"),
    GAME_DETAIL("https://store.steampowered.com/api/appdetails","게임 상세 정보"),
    GAME_REVIEW("https://store.steampowered.com/appreviews/","게임 리뷰 정보"),
    HAVE_GAME("https://api.steampowered.com/IPlayerService/GetOwnedGames/v1","보유 중인 게임");

    private final String url;
    private final String explain;

    SteamApiType(String url, String explain){
        this.url = url;
        this.explain = explain;
    }

}
