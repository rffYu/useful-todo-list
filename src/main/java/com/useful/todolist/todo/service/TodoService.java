package com.useful.todolist.todo.service;

import com.useful.todolist.todo.dto.TodoItemDTO;
import com.useful.todolist.todo.dao.TodoItemEntity;
import com.useful.todolist.todo.repository.TodoRepository;
import com.useful.todolist.todo.TodoMapper;
import com.useful.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<TodoItemDTO> getAllTodos(String userId) {
        List<TodoItemEntity> todos = todoRepository.findByUserId(userId);
        return todos.stream()
                .map(TodoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TodoItemDTO createTodo(String userId, TodoItemDTO todo) {
        TodoItemEntity entity = TodoMapper.toEntity(todo);

        // Check if the user exists
        boolean userExists = userRepository.existsByUserId(entity.getUserId());
        if (!userExists) {
            throw new IllegalArgumentException("Invalid userId: user does not exist");
        }

        TodoItemEntity saved = todoRepository.save(entity);
        return TodoMapper.toDTO(saved);
    }

    public TodoItemDTO updateTodo(String userId, String todoId, TodoItemDTO todoDTO) {
        TodoItemEntity entity = todoRepository.findByTodoIdAndUserId(todoId, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        entity.setTitle(todoDTO.getTitle());
        entity.setContent(todoDTO.getContent());
        entity.setDone(todoDTO.isDone());

        TodoItemEntity updated = todoRepository.save(entity);
        return TodoMapper.toDTO(updated);
    }

    public void deleteTodo(String userId, String todoId) {
        TodoItemEntity entity = todoRepository.findByTodoIdAndUserId(todoId, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todoRepository.delete(entity);
    }
}

