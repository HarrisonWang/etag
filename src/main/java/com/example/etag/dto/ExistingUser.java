package com.example.etag.dto;

import com.example.etag.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExistingUser extends BaseDto<User> {
    
    public ExistingUser() {
        super(User.class);
    }

    private Long id;

    private String name;

    private String email;
    
}
