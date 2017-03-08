package com.darcytech.training.core.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonUtils {

    private static final ObjectMapper om = new ObjectMapper();

    public static String toJson(Object value) {
        try {
            return om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AssertionError(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return om.readValue(json, type);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}
