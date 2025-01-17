package com.minjun.gamerecommend.global.util;

import java.util.*;

public class Extractor {

    public static List<String> extractMaxValues(HashMap<String, Integer> inputMap) {
        if (inputMap.isEmpty()) return new ArrayList<>();

        int maxValue = Collections.max(inputMap.values());

        return findValue(inputMap, maxValue);
    }

    public static List<String > extractMinValues(HashMap<String, Integer> inputMap) {
        int minValue = Collections.min(inputMap.values());
        int maxValue = Collections.max(inputMap.values());

        // 최대 값과 최소값이 동일이면 제외 태그 없음
        if (inputMap.isEmpty() || maxValue == minValue) return new ArrayList<>();

        return findValue(inputMap, minValue);
    }

    private static List<String> findValue(HashMap<String, Integer> inputMap, int value) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            if (entry.getValue() == value) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
