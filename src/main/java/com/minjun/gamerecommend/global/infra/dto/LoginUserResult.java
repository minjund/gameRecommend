package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record LoginUserResult(@JsonProperty("response") UserResponse response) {

    public record UserResponse(@JsonProperty("players") List<HashMap<String,Object>> players) {
    }

}

