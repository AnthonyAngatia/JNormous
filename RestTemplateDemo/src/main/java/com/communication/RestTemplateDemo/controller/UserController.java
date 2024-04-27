package com.communication.RestTemplateDemo.controller;

import com.communication.RestTemplateDemo.dto.UserDto;
import com.communication.RestTemplateDemo.mappers.UserMapper;
import com.communication.RestTemplateDemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/users")
@Slf4j
public class UserController {

    RestTemplate restTemplate;
    UserMapper userMapper;

    public UserController(RestTemplate restTemplate, UserMapper userMapper) {
        this.restTemplate = restTemplate;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public ResponseEntity<String> getUsers() {
        log.info("Get Request");
        try {
            return restTemplate.exchange(
                    "https://jsonplaceholder.typicode.com/userss", HttpMethod.GET, null, String.class);
        } catch (Exception exception) {
            log.error("Cannot find the resource userss");
            exception.printStackTrace();
            return new ResponseEntity<>("Cannot find the resource users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<String> addUsers(@RequestBody UserDto userDto) {
        log.info("UserDto" + userDto);
        HttpEntity<User> requestBody = new HttpEntity<>(userMapper.toUser(userDto));
        log.info("User " + requestBody.getBody());
        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users", HttpMethod.POST, requestBody, String.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsers(@RequestBody User user, @PathVariable("id") Integer id) {
        log.info("PUT request with id " + id + " and Body " + user);
        HttpEntity<User> requestBody = new HttpEntity<>(user);
        log.info("Request body is " + requestBody);
        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users/" + id, HttpMethod.PUT, requestBody, String.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users/" + id, HttpMethod.DELETE, null, String.class);
    }
}
