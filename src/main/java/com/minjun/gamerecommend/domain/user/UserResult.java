package com.minjun.gamerecommend.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record UserResult(@JsonProperty("players") List<HashMap<String,Object>> players) {
}
