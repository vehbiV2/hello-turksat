package com.vehbiozcan.hello_turksat.exception;

public class BaseException extends RuntimeException {
    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.prepareErrorMessage());
    }
}
