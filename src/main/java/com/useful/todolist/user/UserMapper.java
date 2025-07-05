package com.useful.todolist.user;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.dao.UserEntity;

public class UserMapper {

    public static UserDTO toDTO(UserEntity entity) {
        return new UserDTO(entity.getUserId(), entity.getUserName(), entity.getRoleId(), entity.getGroupId());
    }

    public static UserEntity toEntity(UserDTO dto) {
        return new UserEntity(dto.getUserId(), dto.getUserName(), dto.getRoleId(), dto.getGroupId());
    }
}
