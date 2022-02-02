package com.example.demo.controller;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AdmisUser {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createNewUser")
    public ResponseEntity<?> registerNewUser(@RequestBody SignupRequest signUpRequest, Authentication authentication) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPresentation(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = new HashSet<String>(Collections.singletonList(signUpRequest.getRole()));
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            if (role == "admin"){
                Role entrRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role mod is not found."));
                roles.add(entrRole);
                user.setRoles(roles);

            }else  if(role == "employe"){
                Role investRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
                        .orElseThrow(() -> new RuntimeException("Error: Role user is not found."));
                roles.add(investRole);
                user.setRoles(roles);
            }
        });

        user.setRoles(roles);
        userService.createNewAdmin(user, authentication.getName());

        return ResponseEntity.ok(new MessageResponse("New user registered by admin successfully!"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    public User updateUserr(@RequestBody UserUpdateDto userUpdateDto){
        return userService.updateUser(userUpdateDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

/*    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public void deleteUseradmin(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
    }*/

}
