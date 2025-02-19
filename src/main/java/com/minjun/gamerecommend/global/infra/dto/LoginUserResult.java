package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.user.UserResult;

public record LoginUserResult(@JsonProperty("response") UserResult response) { }

