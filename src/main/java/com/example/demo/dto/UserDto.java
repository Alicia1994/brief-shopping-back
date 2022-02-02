package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String presentation;
    private String role;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
