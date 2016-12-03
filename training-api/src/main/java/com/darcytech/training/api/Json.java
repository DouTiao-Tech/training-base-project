package com.darcytech.training.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Json {

    private static ObjectMapper mapper = new ObjectMapper().registerModules(new JavaTimeModule());

    public static String writeValueAsString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T readValue(String s, Class<T> objectClass) throws IOException {
        return mapper.readValue(s, objectClass);
    }

}
