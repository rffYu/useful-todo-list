package com.useful.todolist.user.service;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.dao.UserEntity;
import com.useful.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }

    public UserEntity updateUser(String userId, UserDTO updatedUserData) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        user.setUserName(updatedUserData.getUserName());
        user.setRoleId(updatedUserData.getRoleId());
        user.setGroupId(updatedUserData.getGroupId());

        UserEntity savedUser = userRepository.save(user);
        return savedUser;
    }

    public void deleteUser(String userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        userRepository.delete(user);
    }
}

