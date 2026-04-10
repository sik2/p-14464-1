package com;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {

        String[] cmdBits = cmd.split("\\?");
        actionName = cmdBits[0];

        String queryString = cmdBits.length > 1 ? cmdBits[1].trim() : "";

//        String[] queryStringStringBits = queryString.split("&");
//
//        for (String queryParam : queryStringStringBits) {
//            String[] queryParamBits = queryParam.split("=");
//            String key = queryParamBits[0].trim();
//            String value = queryParamBits.length > 1 ?  queryParamBits[1].trim() : "";
//
//            if (value.isEmpty()) {
//                continue;
//            }

        paramsMap = Arrays.stream(queryString.split("&"))
                .map(p -> p.split("=", 2))
                .filter(bits -> bits.length > 0 && !bits[0].trim().isEmpty() && !bits[1].trim().isEmpty())
                .collect(Collectors.toMap(
                        bits -> bits[0].trim(),
                        bits -> bits[1].trim()
                ));


    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        return paramsMap.getOrDefault(paramName, defaultValue);
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if (value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
