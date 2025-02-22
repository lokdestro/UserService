package com.example.UserService.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private String photo;
}
