package com.example.etag.service;

import java.util.List;

import com.example.etag.dto.NewPassword;
import com.example.etag.entity.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    List<User> list();

    User update(User user);

    void delete(Long id);

    User resetPassword(NewPassword newPassword);
}
