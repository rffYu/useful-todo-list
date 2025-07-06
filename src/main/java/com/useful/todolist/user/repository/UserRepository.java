package com.useful.todolist.user.repository;

import com.useful.todolist.user.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);

    UserEntity updateUser(String userId, UserEntity updatedUserData);

    void deleteByUserId(String userId);
}
