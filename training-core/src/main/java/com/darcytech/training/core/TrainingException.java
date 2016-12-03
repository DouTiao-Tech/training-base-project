package com.darcytech.training.core;

public class TrainingException extends RuntimeException {

    private String errorCode;

    private String errorMessage;

    public TrainingException(String errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public TrainingException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
