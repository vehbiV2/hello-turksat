package com.vehbiozcan.hello_turksat.handler;

import com.vehbiozcan.hello_turksat.exception.ApiError;
import com.vehbiozcan.hello_turksat.exception.BaseException;
import com.vehbiozcan.hello_turksat.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError> handleBaseException(BaseException e, WebRequest request) {
        return ResponseEntity.badRequest().body(customApiError(HttpStatus.BAD_REQUEST,request,e.getMessage()));
    }


    private <T> ApiError<T> customApiError(HttpStatus status, WebRequest request, T message) {
        ApiError<T> apiError = new ApiError<>();
        apiError.setStatus(status.value());

        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setHostname(getHostname());
        exceptionInfo.setPath(request.getDescription(false).substring(4));
        exceptionInfo.setTimeStamp(new Date());
        exceptionInfo.setMessage(message);

        apiError.setExceptionInfo(exceptionInfo);

        return apiError;
    }

    private String getHostname(){
        try{
            /// InetAddress ile biz hostname hostaddress gibi bilgilere erişebiliriz
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Hostname alınırken bir hata oluştu: " + e.getMessage());
        }
        return "unknown";
    }

}
