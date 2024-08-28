package com.example.etag.dto;

import com.example.etag.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewUser extends BaseDto<User> {
    
    public NewUser() {
        super(User.class);
    }

    private String name;

    private String email;

    private String password;

}
