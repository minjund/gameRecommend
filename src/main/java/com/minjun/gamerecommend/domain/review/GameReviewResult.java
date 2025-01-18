package com.minjun.gamerecommend.domain.review;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record GameReviewResult(@JsonProperty("review") List<HashMap<String,Object>> review) {

}
