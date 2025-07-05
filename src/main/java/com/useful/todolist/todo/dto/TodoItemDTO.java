package com.useful.todolist.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemDTO {

    @JsonProperty("todoId")
    private String todoId = "";

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content = "";

    @JsonProperty("done")
    private boolean done = false;
}

