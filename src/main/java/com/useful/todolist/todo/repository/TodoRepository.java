package com.useful.todolist.todo.repository;


import com.useful.todolist.todo.dao.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoItemEntity, String> {

    // Find all todos belonging to a user
    List<TodoItemEntity> findByUserId(String userId);

    // Find one todo by todoId and userId (for ownership check)
    Optional<TodoItemEntity> findByTodoIdAndUserId(String todoId, String userId);
}

