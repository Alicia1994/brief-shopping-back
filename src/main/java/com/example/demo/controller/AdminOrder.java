package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.UserService;
import com.example.demo.security.services.impl.OrderServiceImpl;
import com.example.demo.security.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class AdminOrder {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> adminDeleteOrder(@PathVariable("id") final Long id, Authentication authentication) {
        final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional <User> userOptional = userRepository.findById(user.getId());

        Optional <Order> optionalOrder = orderService.getOrder(id);
        if(optionalOrder.get().getUserId() == userOptional.get().getId()){
            orderService.adminDeleteOrder(optionalOrder.get().getId());
            return new ResponseEntity<String>("delete ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Cette commandde ne vous appartient pas; Vous ne pourrez pas le supprimer", HttpStatus.FORBIDDEN);
        }
    }
}
