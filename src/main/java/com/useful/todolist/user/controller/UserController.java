package com.useful.todolist.user.controller;

import com.useful.todolist.user.UserMapper;
import com.useful.todolist.user.dao.UserEntity;
import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        String userId = authentication.getName();
        Optional<UserEntity> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            UserDTO userDTO = UserMapper.toDTO(user.get());
            return ResponseEntity.ok(userDTO);
        }
    }

    /**
     * Update current user profile.
     */
    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateCurrentUser(
            Authentication authentication,
            @RequestBody UserDTO updatedUserData) {

        String username = authentication.getName();
        UserEntity updatedUser = userRepository.updateUser(username, UserMapper.toEntity(updatedUserData));
        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }

    /**
     * Delete current user.
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteCurrentUser(Authentication authentication) {
        String userId = authentication.getName();
        userRepository.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
