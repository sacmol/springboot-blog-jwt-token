package com.smoilin.io.springbootblog.controller;

import com.smoilin.io.springbootblog.dto.LoginRequest;
import com.smoilin.io.springbootblog.dto.RegisterRequest;
import com.smoilin.io.springbootblog.service.AuthService;
import com.smoilin.io.springbootblog.service.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200/")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
    authService.signup(registerRequest);
    return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {


       return authService.login(loginRequest);
    }
}
