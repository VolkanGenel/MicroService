package com.volkan.exception;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException{
    private final EErrorType errorType;

    public UserServiceException(EErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public UserServiceException(EErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }


}
