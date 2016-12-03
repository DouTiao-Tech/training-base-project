package com.darcytech.training.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DuplicateException extends RuntimeException {

    private String errorCode;

    @JsonCreator
    public DuplicateException(@JsonProperty("errorCode") String errorCode,
                              @JsonProperty("errorMessage") String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
