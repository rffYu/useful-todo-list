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

    public List<TodoItemDTO> getAllTodos(String username) {
        List<TodoItemEntity> todos = todoRepository.findByUsername(username);
        return todos.stream()
                .map(TodoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TodoItemDTO createTodo(String username, TodoItemDTO todo) {
        TodoItemEntity entity = TodoMapper.toEntity(todo);
        boolean userExists = userRepository.existsById(todo.getUserId());
        if (!userExists) {
            throw new IllegalArgumentException("Invalid userId: user does not exist");
        }
        TodoItemEntity saved = todoRepository.save(entity);
        return TodoMapper.toDTO(saved);
    }

    public TodoItemDTO updateTodo(String username, String todoId, TodoItemDTO todoDTO) {
        TodoItemEntity entity = todoRepository.findByTodoIdAndUsername(todoId, username)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        entity.setTitle(todoDTO.getTitle());
        entity.setContent(todoDTO.getContent());
        entity.setDone(todoDTO.getDone());

        TodoItemEntity updated = todoRepository.save(entity);
        return TodoMapper.toDTO(updated);
    }

    public void deleteTodo(String username, String todoId) {
        TodoItemEntity entity = todoRepository.findByTodoIdAndUsername(todoId, username)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todoRepository.delete(entity);
    }
}

