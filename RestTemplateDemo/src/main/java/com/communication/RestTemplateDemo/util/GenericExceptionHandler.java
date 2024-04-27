package com.communication.RestTemplateDemo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import java.net.SocketTimeoutException;

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(SocketTimeoutException.class)
    private ResponseEntity<String> handleTimeoutError(){
        return new ResponseEntity<>("SocketTimeout Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    private ResponseEntity<String> handleClientErrorException(HttpClientErrorException exception){
        log.error("Exception message is " + exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>("Not Found. Check the request being made", HttpStatus.NOT_FOUND);
    }
}
