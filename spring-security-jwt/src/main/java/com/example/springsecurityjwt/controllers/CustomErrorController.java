package com.example.springsecurityjwt.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<String> handleError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }

    @GetMapping("/")
    public ResponseEntity<String> emptyResponse() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource Not Found at custom error controller");
    }
}
