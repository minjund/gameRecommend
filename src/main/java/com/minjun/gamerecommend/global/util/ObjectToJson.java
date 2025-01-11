package com.minjun.gamerecommend.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
    
    public static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static String convert(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
