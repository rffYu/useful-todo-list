package com.useful.todolist.todo.controller;

import com.useful.todolist.todo.dto.TodoItemDTO;
import com.useful.todolist.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
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
        String username = authentication.getName();
        List<TodoItemDTO> todos = todoService.getAllTodos(username);
        return ResponseEntity.ok(todos);
    }

    /**
     * Create a new todo for the current user.
     */
    @PostMapping
    public ResponseEntity<TodoItemDTO> createTodo(Authentication authentication, @RequestBody TodoItemDTO todoDTO) {
        String username = authentication.getName();
        TodoItemDTO createdTodo = todoService.createTodo(username, todoDTO);
        return ResponseEntity.ok(createdTodo);
    }

    /**
     * Update a todo by ID for the current user.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoItemDTO> updateTodo(Authentication authentication,
                                              @PathVariable String id,
                                              @RequestBody TodoItemDTO todoDTO) {
        String username = authentication.getName();
        TodoItemDTO updatedTodo = todoService.updateTodo(username, id, todoDTO);
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

