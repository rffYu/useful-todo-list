package com.useful.todolist.user.controller;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get current user profile based on authentication token.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        String userId = authentication.getName();
        UserDTO user = userService.findByUserId(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Update current user profile.
     */
    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateCurrentUser(
            Authentication authentication,
            @RequestBody UserDTO updatedUserData) {

        String username = authentication.getName();
        UserDTO updatedUser = userService.updateUser(username, updatedUserData);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete current user.
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteCurrentUser(Authentication authentication) {
        String userId = authentication.getName();
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
