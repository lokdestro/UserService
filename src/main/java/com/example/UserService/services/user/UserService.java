package com.example.UserService.services.user;

import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.domain.model.User;
import com.example.UserService.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User GetByNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User GetCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String phoneNumber = (String) ((User) authentication.getPrincipal()).getPhoneNumber();
            return GetByNumber(phoneNumber);
        }
        throw new UsernameNotFoundException("Current user not found");
    }

    public User Create(User user) {
        if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new RuntimeException("Пользователь с таким номером уже существует");
        }
        return userRepo.save(user);
    }

    public void DeleteCurrentUser() {
        User currentUser = GetCurrentUser();
        if (currentUser != null) {
            userRepo.delete(currentUser);
        } else {
            throw new UsernameNotFoundException("Current user not found");
        }
    }

    public void Update(UpdateRequest updateRequest) {
        User user = GetByNumber(updateRequest.getPhoneNumber());
        System.out.println("UPDATE 2");
        if (updateRequest.getName() != null && !updateRequest.getName().isEmpty()) {
            user.setName(updateRequest.getName());
        }

        if (updateRequest.getEmail() != null && !updateRequest.getEmail().isEmpty()) {
            user.setEmail(updateRequest.getEmail());
        }

        if (updateRequest.getPhoto() != null && !updateRequest.getPhoto().isEmpty()) {
            user.setPhoto(updateRequest.getPhoto());
        }

        if (updateRequest.getPhoneNumber() != null && !updateRequest.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(updateRequest.getPhoneNumber());
        }
        System.out.println("UPDATE 3");
        userRepo.save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::GetByNumber;
    }
}
