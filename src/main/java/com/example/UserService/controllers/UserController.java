package com.example.UserService.controllers;

import com.example.UserService.domain.dto.DeleteRequest;
import com.example.UserService.domain.dto.GetResponse;
import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<GetResponse> get() {
        System.out.println("GET");
        var user = userService.GetCurrentUser();
        System.out.println("END GET");
        var userResp = GetResponse.builder()
                .name(user.getName())
                .phoneNumber(String.valueOf(user.getPhoneNumber()))
                .email(user.getEmail())
                .photo(user.getPhoto())
                .build();

        return ResponseEntity.ok(userResp);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody UpdateRequest UpdateRequest) {
        System.out.println("UPDATE");
        userService.Update(UpdateRequest);
        return ResponseEntity.ok("Профиль успешно обновлен");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody DeleteRequest deleteUserProfileRequest) {
        userService.DeleteCurrentUser();
        return ResponseEntity.ok("Профиль успешно удален");
    }
}
