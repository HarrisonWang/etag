package com.example.etag.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.etag.annotation.ActionMapping;
import com.example.etag.entity.User;
import com.example.etag.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<String> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        String etag = "\"" + user.hashCode() + "\"";
        return ResponseEntity.ok()
                .eTag(etag)
                .body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user,
            @RequestHeader(value = "If-Match", required = false) String ifMatch) {
        User existingUser = userService.getUser(id);
        String currentEtag = "\"" + existingUser.hashCode() + "\"";
        if (ifMatch != null && !currentEtag.equals(ifMatch)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .eTag(currentEtag)
                    .body(existingUser);
        }

        user.setId(id);
        User updatedUser = userService.updateUser(user);
        String newEtag = "\"" + updatedUser.hashCode() + "\"";

        return ResponseEntity.ok()
                .eTag(newEtag)
                .body(updatedUser);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        String etag = "\"" + createdUser.hashCode() + "\"";
        return ResponseEntity.status(HttpStatus.CREATED)
                .eTag(etag)
                .body(createdUser);
    }

    @ActionMapping("activate")
    public String activateUser(Long id) {
        return "User " + id + " activated";
    }

    @ActionMapping("deactivate")
    public String deactivateUser(Long id) {
        return "User " + id + " deactivated";
    }

    @ActionMapping("resetPassword")
    public String resetUserPassword(Long id) {
        return "Password reset initiated for user " + id;
    }

}
