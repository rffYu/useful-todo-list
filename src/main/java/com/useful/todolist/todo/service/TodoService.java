package com.useful.todolist.todo.service;

import com.useful.todolist.todo.dto.TodoItemDTO;
import com.useful.todolist.user.mapper.UserMapper;
import com.useful.todolist.todo.mapper.TodoItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoItemMapper todoItemMapper;
    private final UserMapper userMapper;

    @Autowired
    public TodoService(TodoItemMapper todoItemMapper, UserMapper userMapper) {
        this.todoItemMapper = todoItemMapper;
        this.userMapper = userMapper;
    }

    public List<TodoItemDTO> getAllTodos(String userId) {
        return todoItemMapper.findByUserId(userId);
    }

    public TodoItemDTO createTodo(String userId, TodoItemDTO todo) {

        // Check if the user exists
        boolean userExists = userMapper.existsByUserId(userId);
        if (!userExists) {
            throw new IllegalArgumentException("Invalid userId: user does not exist");
        }

        todoItemMapper.save(todo);
        return todo;
    }

    public TodoItemDTO updateTodo(String userId, String todoId, TodoItemDTO todoDTO) throws RuntimeException {
        TodoItemDTO entity = todoItemMapper.findByTodoIdAndUserId(todoId, userId);
        if (entity == null) throw new RuntimeException("Todo not found or access denied");

        entity.setTitle(todoDTO.getTitle());
        entity.setContent(todoDTO.getContent());
        entity.setDone(todoDTO.isDone());

        todoItemMapper.save(entity);
        return entity;
    }

    public void deleteTodo(String userId, String todoId) throws RuntimeException {
        TodoItemDTO entity = todoItemMapper.findByTodoIdAndUserId(todoId, userId);
        if (entity == null) throw new RuntimeException("Todo not found or access denied");

        todoItemMapper.delete(todoId);
    }
}

