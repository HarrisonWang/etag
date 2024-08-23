package com.example.etag.service.impl;

import org.springframework.stereotype.Service;

import com.example.etag.entity.User;
import com.example.etag.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(Long id) {
        return new User(id, "John Doe", "john@example.com", "ACTIVE", "password");
    }

    @Override
    public User updateUser(User user) {
        User existingUser = getUser(user.getId());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return existingUser;
    }

}
