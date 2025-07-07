package com.useful.todolist.user.controller;

import com.useful.todolist.user.UserMapper;
import com.useful.todolist.user.dao.UserEntity;
import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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
        UserEntity user = userService.findByUserId(userId);
        UserDTO userDTO = UserMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Update current user profile.
     */
    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateCurrentUser(
            Authentication authentication,
            @RequestBody UserDTO updatedUserData) {

        String username = authentication.getName();
        UserEntity updatedUser = userService.updateUser(username, updatedUserData);
        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
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
