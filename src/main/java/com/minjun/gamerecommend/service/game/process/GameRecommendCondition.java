package com.minjun.gamerecommend.service.game.process;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GameRecommendCondition {
    private Query query;
    private Context context;
    private DataRequest data_request;

    public static GameRecommendCondition create(List<List<String>> tagIdList) {
        return GameRecommendCondition.builder()
                .query(Query.builder()
                        .count("8")
                        .sort("12")
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
                        .build())
                .data_request(DataRequest.builder()
                        .include_basic_info(true)
                        .build())
                .build();
    }

    @Getter
    @Builder
    public static class Query {
        private String count;
        private Filters filters;
        private String sort;

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
//        private String steam_realm;
    }

    @Getter
    @Builder
    public static class DataRequest {
        private boolean include_basic_info;
    }
}
