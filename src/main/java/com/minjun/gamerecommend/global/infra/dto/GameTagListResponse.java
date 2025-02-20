package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record GameTagListResponse(@JsonProperty("tags") List<HashMap<String , String>> tags) {
}
