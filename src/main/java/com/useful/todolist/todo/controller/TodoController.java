package com.useful.todolist.todo.controller;

import com.useful.todolist.todo.dto.TodoItemDTO;
import com.useful.todolist.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Get all todos for the current user.
     */
    @GetMapping
    public ResponseEntity<List<TodoItemDTO>> getAllTodos(Authentication authentication) {
        String userId = authentication.getName();
        List<TodoItemDTO> todos = todoService.getAllTodos(userId);
        return ResponseEntity.ok(todos);
    }

    /**
     * Create a new todo for the current user.
     */
    @PostMapping
    public ResponseEntity<TodoItemDTO> createTodo(Authentication authentication, @RequestBody TodoItemDTO todoDTO) {
        String userId = authentication.getName();
        TodoItemDTO createdTodo = todoService.createTodo(userId, todoDTO);
        return ResponseEntity.ok(createdTodo);
    }

    /**
     * Update a todo by ID for the current user.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoItemDTO> updateTodo(Authentication authentication,
                                              @PathVariable String todoId,
                                              @RequestBody TodoItemDTO todoDTO) {
        String userId = authentication.getName();
        TodoItemDTO updatedTodo = todoService.updateTodo(userId, todoId, todoDTO);
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     * Delete a todo by ID for the current user.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(Authentication authentication, @PathVariable String id) {
        String username = authentication.getName();
        todoService.deleteTodo(username, id);
        return ResponseEntity.noContent().build();
    }
}

