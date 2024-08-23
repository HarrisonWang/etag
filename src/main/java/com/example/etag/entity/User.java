package com.example.etag.entity;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {

    private Long id;

    private String name;

    private String email;

    private String status;

    private String password;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, status, password);
    }
}
