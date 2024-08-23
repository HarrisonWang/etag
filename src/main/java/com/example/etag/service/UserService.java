package com.example.etag.service;

import com.example.etag.entity.User;

public interface UserService {

    User getUser(Long id);

    User updateUser(User user);

}
