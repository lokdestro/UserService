package com.example.UserService.services.auth;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.domain.model.User;
import com.example.UserService.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse SignUp(SignUpRequest request) {
        System.out.println("SERVICE");
        System.out.println(request.getName());
        System.out.println(request.getPhoneNumber());
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();

        userService.Create(user);
        System.out.println("SERVICE2");
        var jwt = jwtService.generateToken(user);
        System.out.println("SERVICE1");
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        System.out.println("SERVICE");
        System.out.println(request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPhoneNumber(),
                request.getPassword()
        ));
        System.out.println("SERVICE2");
        var user = userService.GetByNumber(request.getPhoneNumber());
        System.out.println("SERVICE3");
        var jwt = jwtService.generateToken(user);
        System.out.println("SERVICE4");
        return new JwtAuthResponse(jwt);
    }


}
