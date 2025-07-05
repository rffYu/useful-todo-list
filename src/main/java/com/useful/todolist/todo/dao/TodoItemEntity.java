package com.useful.todolist.todo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "todo-item")
@Data
@NoArgsConstructor
public class TodoItemEntity {

    @Id
    private String todoId;

    private String userId;

    private String title;

    private String content = "";

    private boolean done = false;

    public TodoItemEntity(String todoId, String userId, String title, boolean done) {
        if (todoId == null || todoId.isEmpty()) {
            this.todoId = UUID.randomUUID().toString();
        } else {
            this.todoId = todoId;
        }

        this.userId = userId;

        this.title = title;

        this.done = done;
    }
}
