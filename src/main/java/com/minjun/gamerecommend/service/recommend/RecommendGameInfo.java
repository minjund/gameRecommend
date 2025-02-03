package com.minjun.gamerecommend.service.recommend;

import lombok.Builder;
import org.springframework.util.StringUtils;

@Builder
public record RecommendGameInfo(UserId userId) {
    public static RecommendGameInfo from(String id){
        return new RecommendGameInfo(UserId.from(id));
    }

    public record UserId(String userId){
        public UserId {
            if(!isValidate(userId)){
                throw new IllegalArgumentException("userID 값이 비어 있습니다.");
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
