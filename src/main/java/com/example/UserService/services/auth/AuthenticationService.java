package com.example.UserService.services.auth;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.domain.model.User;
import com.example.UserService.services.Jwt.JwtService;
import com.example.UserService.services.Jwt.JwtServiceImpl;
import com.example.UserService.services.user.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthService {
    private final UserSerivce userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse SignUp(SignUpRequest request) {
        System.out.println(request.getName());
        System.out.println(request.getPhoneNumber());
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();

        userService.Create(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        System.out.println(request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPhoneNumber(),
                request.getPassword()
        ));
        var user = userService.GetByNumber(request.getPhoneNumber());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }


}
