package com.communication.RestTemplateDemo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        if(httpResponse.getStatusCode().is5xxServerError() ||
                httpResponse.getStatusCode().is4xxClientError()){
            log.error("An error when calling extrnal service using rest template");
            return true;
        }
        return false;

    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            //Handle SERVER_ERROR
            log.info("Issa server-side error");
            throw new HttpServerErrorException(httpResponse.getStatusCode());
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            //Handle CLIENT_ERROR
            log.info("Issa client side error");
            log.info("Http response is" + httpResponse);
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new HttpClientErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
            }
        }

    }
}
