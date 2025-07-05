package com.useful.todolist.user.controller;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get current user profile based on authentication token.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        UserDTO user = userRepository.findByUsername(username);
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
        UserDTO updatedUser = userRepository.updateUser(username, updatedUserData);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * (Optional) Delete current user.
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        userRepository.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}

