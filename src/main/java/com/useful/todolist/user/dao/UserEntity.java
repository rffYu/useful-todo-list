package com.useful.todolist.user.dao;

import com.useful.todolist.user.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    private String userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private Role roleId;

    private String groupId;

    public UserEntity(String userId, String userName, Role roleId, String groupId) {
        if (userId == null || userId.isEmpty()) {
            this.userId = UUID.randomUUID().toString();
        } else {
            this.userId = userId;
        }

        this.userName = userName;
        this.roleId = roleId;
        this.groupId = groupId;
    }
}

