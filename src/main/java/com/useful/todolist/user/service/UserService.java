package com.useful.todolist.user.service;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.dao.UserEntity;
import com.useful.todolist.user.repository.UserRepository;
import com.useful.todolist.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findByUsername(String username) {
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return UserMapper.toDTO(user);
    }

    public UserDTO updateUser(String username, UserDTO updatedUserData) {
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        user.setUserName(updatedUserData.getUserName());
        user.setRoleId(updatedUserData.getRoleId());
        user.setGroupId(updatedUserData.getGroupId());

        UserEntity savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        userRepository.delete(user);
    }
}

