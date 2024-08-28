package com.example.etag.dto;

import com.example.etag.annotation.pathinject.PathIdAware;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewPassword implements PathIdAware {

    private Long id;
    
    private String currentPassword;

    private String newPassword;

}
