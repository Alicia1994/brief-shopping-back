package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserUpdateDto {
    private Long id;
    private String username;
    private String email;
    private String presentation;
    private String password;
    private Set<Role> roles = new HashSet<>();

}
