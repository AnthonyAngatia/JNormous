package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.MyUserDetailsService;
import com.example.springsecurityjwt.jwt.JwtUtil;
import com.example.springsecurityjwt.models.AuthenticationRequest;
import com.example.springsecurityjwt.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

//    public HelloResource(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        System.out.println("At hello");
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("At POST authenticate");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.username(), authenticationRequest.password()
            ));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

        var userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.username());
        var jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate() {
        System.out.println("At GET authenticate");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    "username", "password"
            ));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

        var userDetails = myUserDetailsService.loadUserByUsername("username");
        var jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));
    }
}
