package com.useful.todolist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.useful.todolist.user.mapper")
@MapperScan("com.useful.todolist.todo.mapper")
public class UsefulTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsefulTodoListApplication.class, args);
    }
}
