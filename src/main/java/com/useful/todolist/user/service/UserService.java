package com.useful.todolist.user.service;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDTO findByUserId(String userId) throws RuntimeException {
        UserDTO user = userMapper.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found or access denied");
        }
        return user;
    }

    public UserDTO updateUser(String userId, UserDTO updatedUserData) throws RuntimeException {
        UserDTO user = userMapper.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found or access denied");
        }

        user.setUserName(updatedUserData.getUserName());
        user.setRoleId(updatedUserData.getRoleId());
        user.setGroupId(updatedUserData.getGroupId());

        userMapper.save(user);
        return user;
    }

    public void deleteUser(String userId) {
        userMapper.delete(userId);
    }
}

