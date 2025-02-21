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
        System.out.println("GET BU NUMBER");
        System.out.println(phoneNumber);
        var res = userRepo.findByPhoneNumber(phoneNumber).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println(res.getName());
        System.out.println(res.getPassword());
        return res;
    }

    public User GetCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("GET 1");
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String phoneNumber = (String) ((User) authentication.getPrincipal()).getPhoneNumber();
            System.out.println("GET 3");
            return GetByNumber(phoneNumber);
        }
        System.out.println("GET 2");
        throw new UsernameNotFoundException("Current user not found");
    }

    public User Create(User user) {
        System.out.println("USER SERVICE");
        if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new RuntimeException("Пользователь с таким номером уже существует");
        }
        System.out.println("USER SERVICE1");
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

        userRepo.save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::GetByNumber;
    }
}
