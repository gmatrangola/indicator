package com.matrangola.indicator.validation;

public class NoSuchElementResponse {
    private String errorMessage;

    public NoSuchElementResponse(String localizedMessage) {
        this.errorMessage = localizedMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}