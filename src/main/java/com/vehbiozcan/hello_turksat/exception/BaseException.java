package com.vehbiozcan.hello_turksat.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private MessageType messageType;

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.prepareErrorMessage());
        /// ErrorMessage üzerinde error kodumuzu alıp setledik bunu GlobalException içinde swtich-case
        /// yapısında kullanacağız
        this.messageType = errorMessage.getMessageType();
    }
}
