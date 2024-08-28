package com.example.etag.controller;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.etag.annotation.pathinject.InjectId;
import com.example.etag.dto.ExistingUser;
import com.example.etag.dto.NewPassword;
import com.example.etag.dto.NewUser;
import com.example.etag.dto.UpdatedUser;
import com.example.etag.entity.User;
import com.example.etag.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExistingUser> get(@PathVariable Long id) {
        User user = userService.get(id);
        String etag = "\"" + user.hashCode() + "\"";
        ExistingUser existingUser = ExistingUser.fromEntity(user, ExistingUser.class);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .eTag(etag)
                .body(existingUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        List<User> users = userService.list();
        String etag = "\"" + Objects.hash(users) + "\"";
        return ResponseEntity.ok()
                .eTag(etag)
                .body(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExistingUser> update(@PathVariable Long id, @InjectId @RequestBody UpdatedUser updatedUser,
            @RequestHeader(value = "If-Match", required = false) String ifMatch) {
        User entity = userService.get(id);
        String currentEtag = "\"" + entity.hashCode() + "\"";
        if (!Objects.equals(ifMatch, currentEtag)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .eTag(currentEtag)
                    .build();
        }

        User user = userService.update(updatedUser.toEntity());
        ExistingUser existingUser = ExistingUser.fromEntity(user, ExistingUser.class);
        return ResponseEntity.ok(existingUser);
    }

    @PostMapping
    public ResponseEntity<ExistingUser> create(@RequestBody NewUser newUser) {
        User user = userService.create(newUser.toEntity());
        ExistingUser existingUser = ExistingUser.fromEntity(user, ExistingUser.class);
        return ResponseEntity.ok(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}:activate")
    public String activate(@PathVariable Long id) {
        return "User " + id + " activated";
    }

    @PostMapping("/{id}:deactivate")
    public String deactivate(@PathVariable Long id) {
        return "User " + id + " deactivated";
    }

    @PostMapping("/{id}:resetPassword")
    public ResponseEntity<ExistingUser> resetPassword(@PathVariable Long id, @InjectId @RequestBody NewPassword newPassword) {
        User user = userService.resetPassword(newPassword);
        ExistingUser existingUser = ExistingUser.fromEntity(user, ExistingUser.class);
        return ResponseEntity.ok(existingUser);
    }

}
