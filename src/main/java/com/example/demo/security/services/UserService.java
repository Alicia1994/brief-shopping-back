package com.example.demo.security.services;


import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.User;
import com.example.demo.payload.request.SearchRequest;

import java.util.Optional;

public interface UserService {
    Iterable<User> getAllUser();

    Optional<User> getUser(Long id);

    User saveUser(User u);

    Optional<User> SearchUser(String username);

    User createNewAdmin(User user, String username);

    User updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

}
