package com.example.UserService.services.user;

import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public interface UserSerivce extends UserDetailsService {
    User GetByNumber(String phoneNumber);
    User GetCurrentUser();
    User Create(User user);
    void DeleteCurrentUser();
    void Update(UpdateRequest updateRequest) throws IOException;
    UserDetailsService userDetailsService();
}
