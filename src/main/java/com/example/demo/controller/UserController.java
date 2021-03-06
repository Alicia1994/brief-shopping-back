package com.example.demo.controller;


import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")public class UserController {

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


/*    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createAdmin")
    public ResponseEntity<?> registerAdminUser(@RequestBody SignupRequest signUpRequest, Authentication authentication) {
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
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        user.setRoles(roles);

        userService.createNewAdmin(user, authentication.getName());

        return ResponseEntity.ok(new MessageResponse("New user registered by admin successfully!"));
    }*/


    @GetMapping("")
    public Iterable<User> listUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @GetMapping("/searchUser")
    public Optional<User> searchUser(@RequestBody SearchRequest searchRequest) {
        return userService.SearchUserForm(searchRequest);
    }

    @GetMapping("/search/{username}")
    public Optional<User> searchUserForm(@PathVariable String username) {
        return userService.SearchUser(username);
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR', 'ROLE_INVESTISSEUR')")
    @PutMapping
    public User updateUser(@RequestBody User u){
        return userService.saveUser(u);
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR', 'ROLE_INVESTISSEUR')")
    @PutMapping("/modif")
    public User updateUser(@RequestBody UserUpdateDto userUpdateDto){
        Optional <User> optionalUser = userService.getUser(userUpdateDto.getId());
        userUpdateDto.setRoles(optionalUser.get().getRoles());
        if(optionalUser.isPresent()){
            return userService.updateUser(userUpdateDto);
        }else {
            return null;
        }
    }


}
