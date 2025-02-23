package com.example.UserService.controllers;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.services.auth.AuthService;
import com.example.UserService.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody SignUpRequest request) {
        JwtAuthResponse response = authenticationService.SignUp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest request) {
        JwtAuthResponse response = authenticationService.signIn(request);
        return ResponseEntity.ok(response);
    }
}
