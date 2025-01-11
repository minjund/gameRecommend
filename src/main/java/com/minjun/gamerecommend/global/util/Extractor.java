package com.minjun.gamerecommend.global.util;

import java.util.*;

public class Extractor {

    public static List<String> extractMaxValues(HashMap<String, Integer> inputMap) {
        if (inputMap.isEmpty()) return new ArrayList<>();

        // 최대 값 찾기
        int maxValue = Collections.max(inputMap.values());

        // 최대 값과 같은 항목들을 새로운 HashMap으로 반환
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static List<String > extractMinValues(HashMap<String, Integer> inputMap) {
        if (inputMap.isEmpty()) return new ArrayList<>();

        // 최소 값 찾기
        int minValue = Collections.min(inputMap.values());

        // 최소 값과 같은 항목들을 새로운 HashMap으로 반환
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            if (entry.getValue() == minValue) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
