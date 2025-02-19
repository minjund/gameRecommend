package com.minjun.gamerecommend.domain.user;

import org.springframework.util.StringUtils;

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

    public String value(){
        return userId;
    }
}
