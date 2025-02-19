package com.minjun.gamerecommend.service.recommend.query;

import org.springframework.util.StringUtils;

public record RecommendDetailCondition(AppId appId) {
    public static RecommendDetailCondition from(String appId) {
        return new RecommendDetailCondition(AppId.from(appId));
    }

    public record AppId(String appId){
        public AppId {
            if (isValid()){
                throw new IllegalArgumentException("빈 ID가 넘어 올 수 없습니다.");
            }
        }
        public static AppId from(String appId){
            return new AppId(appId);
        }

        public String stringValue() {
            return appId;
        }

        private boolean isValid(){
            return !StringUtils.hasLength(appId);
        }

    }
}
