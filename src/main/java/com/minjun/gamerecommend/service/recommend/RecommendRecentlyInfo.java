package com.minjun.gamerecommend.service.recommend;

import lombok.Builder;
import org.springframework.util.StringUtils;

@Builder
public record RecommendRecentlyInfo(UserId userId) {
    public static RecommendRecentlyInfo from(String id){
        return new RecommendRecentlyInfo(UserId.from(id));
    }

    public record UserId(String userId){
        public UserId {
            if(!isValidate(userId)){
                throw new IllegalArgumentException("UserId must not be empty");
            }
        }

        public static UserId from(String id) {
            return new UserId(id);
        }

        private boolean isValidate(String id){
            return StringUtils.hasLength(id);
        }
    }
}
