package com.example.demo.security.services.impl;


import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public User saveUser(User u) {
        User save = userRepository.save(u);
        return save;
    }

    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(UserUpdateDto userUpdateDto){
        User user = modelMapper.map(userUpdateDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public  User createNewAdmin(User user, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User newUser = null;
        Optional<Role> roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
        if(userOptional.isPresent() && roleAdmin.isPresent()){
            newUser = userRepository.save(user);
        }
        return newUser;
    }

}