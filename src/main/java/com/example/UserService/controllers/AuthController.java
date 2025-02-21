package com.example.UserService.controllers;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody SignUpRequest request) {
        System.out.println("CONTROLLER");
        JwtAuthResponse response = authenticationService.SignUp(request);
        System.out.println("RESPONSE: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest request) {
        System.out.println("CONTROLLER");
        JwtAuthResponse response = authenticationService.signIn(request);
        System.out.println("END SIGNIN");
        return ResponseEntity.ok(response);
    }
}
