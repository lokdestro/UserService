package com.example.UserService.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetResponse {
    private String name;
    private String phoneNumber;
    private String email;
    private String photo;
}
