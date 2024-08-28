package com.example.etag.dto;

import com.example.etag.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdatedUser extends BaseDto<User> {
    
    public UpdatedUser() {
        super(User.class);
    }

    private Long id;

    private String name;

    private String email;
    
}
