package com.example.UserService.services.auth;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;

public interface AuthService {
    JwtAuthResponse SignUp(SignUpRequest request);
    JwtAuthResponse signIn(SignInRequest request);
}
