package com.example.etag.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.etag.dto.NewPassword;
import com.example.etag.entity.User;
import com.example.etag.service.BaseService;
import com.example.etag.service.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    // 模拟一个 User 列表
    private final List<User> users = new ArrayList<>(Arrays.asList(
        new User(1L, "John Doe", "johndo@example.com", "ACTIVE", "password"),
        new User(2L, "Harrison Wang", "harrisonwang@example.com", "ACTIVE", "password"),
        new User(3L, "Tom Smith", "tomsmith@example.com", "ACTIVE", "password")
    ));

    @Override
    public User create(User user) {
        return new User(1L, user.getName(), user.getEmail(), "ACTIVE", user.getPassword());
    }

    @Override
    public User get(Long id) {
        User entity = users.stream().filter(user -> user.getId().equals(id)).findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));
        // return ExistingUser.fromEntity(entity, ExistingUser.class);
        // return convertToDto(entity, ExistingUser.class);
        return entity;
    }

    @Override
    public List<User> list() {
        return users;
    }

    @Override
    public User update(User updatedUser) {
        User entity = users.stream().filter(user -> user.getId().equals(updatedUser.getId())).findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));
        copyNonNullProperties(updatedUser, entity);
        // 执行更新
        // return convertToDto(entity, ExistingUser.class);
        return entity;
    }

    @Override
    public void delete(Long id) {
        // TODO: 
    }

    @Override
    public User resetPassword(NewPassword newPassword) {
        User entity = users.stream().filter(user -> user.getId().equals(newPassword.getId())).findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));
        if (Objects.equals(newPassword.getCurrentPassword(), entity.getPassword())) {
            entity.setPassword(newPassword.getNewPassword());
            return entity;
        } else {
            throw new RuntimeException("原密码不匹配！");
        }
    }

}
