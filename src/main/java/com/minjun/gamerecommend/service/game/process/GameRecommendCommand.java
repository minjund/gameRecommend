package com.minjun.gamerecommend.service.game.process;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GameRecommendCommand {
    private Query query;
    private Context context;

    public static GameRecommendCommand createGameRecommendCommand(List<List<String>> tagIdList) {
        return GameRecommendCommand.builder()
                .query(Query.builder()
                        .count("5")
                        .filters(Query.Filters.builder()
                                .released_only(true)
                                .type_filters(Query.Filters.TypeFilters.builder()
                                        .include_games(true)
                                        .build())
                                .tagids_must_match(List.of(new Query.Filters.TagIds(
                                        tagIdList.getFirst()  // 첫 번째 리스트를 사용
                                )))
                                .tagids_exclude(tagIdList.get(1))
                                .build())
                        .build())
                .context(Context.builder()
                        .language("en")
                        .elanguage("en")
                        .country_code("KR")
                        .steam_realm("1")
                        .build())
                .build();
    }

    @Getter
    @Builder
    public static class Query {
        private String count;
        private Filters filters;

        @Getter
        @Builder
        public static class Filters {
            private boolean released_only;
            private TypeFilters type_filters;
            private List<TagIds> tagids_must_match;
            private List<String> tagids_exclude;

            @Getter
            @Builder
            public static class TypeFilters {
                private boolean include_games;
            }

            @Getter
            @AllArgsConstructor
            public static class TagIds {
                private List<String> tagids;
            }
        }
    }

    @Getter
    @Builder
    public static class Context {
        private String language;
        private String elanguage;
        private String country_code;
        private String steam_realm;
    }

//    @Getter
//    @Builder
//    public static class DataRequest {
//        private boolean includeAssets;
//        private boolean includeRelease;
//        private boolean includePlatforms;
//        private boolean includeAllPurchaseOptions;
//        private boolean includeScreenshots;
//        private boolean includeTrailers;
//        private boolean includeRatings;
//        private String includeTagCount;
//        private boolean includeReviews;
//        private boolean includeBasicInfo;
//        private boolean includeSupportedLanguages;
//        private boolean includeFullDescription;
//        private boolean includeIncludedItems;
//        private boolean includeAssetsWithoutOverrides;
//        private boolean applyUserFilters;
//        private boolean includeLinks;
//    }
}
