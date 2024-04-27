package com.communication.RestTemplateDemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class UserService {

    RestTemplate restTemplate;
    public void getUsers(){
        restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users", HttpMethod.GET,null,String.class);
    }
}
