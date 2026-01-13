package com.example.spring_security_blog_tutorial.controllers;

import com.example.spring_security_blog_tutorial.domain.dtos.AuthResponse;
import com.example.spring_security_blog_tutorial.domain.dtos.LoginRequest;
import com.example.spring_security_blog_tutorial.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        UserDetails userDetails =  authenticationService
                .authenticate(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                );
        var token =  authenticationService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token,86400));
    }



}
