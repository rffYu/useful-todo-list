package com.useful.todolist.todo;


import com.useful.todolist.todo.dao.TodoItemEntity;
import com.useful.todolist.todo.dto.TodoItemDTO;

public class TodoMapper {

    public static TodoItemDTO toDTO(TodoItemEntity entity) {
        if (entity == null) return null;

        return new TodoItemDTO(
            entity.getTodoId(),
            entity.getUserId(),
            entity.getTitle(),
            entity.getContent(),
            entity.isDone()
        );
    }

    public static TodoItemEntity toEntity(TodoItemDTO dto) {
        if (dto == null) return null;

        return new TodoItemEntity(
            dto.getTodoId(),
            dto.getUserId(),
            dto.getTitle(),
            dto.isDone()
        );
    }
}

