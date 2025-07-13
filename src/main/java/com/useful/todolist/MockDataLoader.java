package com.useful.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.useful.todolist.todo.mapper.TodoItemMapper;
import com.useful.todolist.todo.dto.TodoItemDTO;
import com.useful.todolist.user.mapper.UserMapper;
import com.useful.todolist.user.dto.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
class InitialData {
    private List<UserDTO> users;
    private List<TodoItemDTO> todos;
}

@Component
public class MockDataLoader implements CommandLineRunner {

    private final UserMapper userMapper;
    private final TodoItemMapper todoItemMapper;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public MockDataLoader(UserMapper userMapper, TodoItemMapper todoMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.todoItemMapper = todoMapper;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            System.out.println("Please provide JSON file path as the first argument.");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()) {
            System.err.println("File not found: " + args[0]);
            return;
        }

        InitialData data = objectMapper.readValue(file, InitialData.class);

        if (data.getUsers() != null) {
            userMapper.deleteAll();

            List<UserDTO> encodedUsers = data.getUsers().stream()
            .peek(user -> {
                user.setPassword(passwordEncoder.encode(user.getPassword())); // 💡 encode password here
            })
            .toList();

            userMapper.saveAll(encodedUsers);
            System.out.println("Loaded " + data.getUsers().size() + " users.");
        }

        if (data.getTodos() != null) {
            todoItemMapper.deleteAll();
            todoItemMapper.saveAll(data.getTodos());
            System.out.println("Loaded " + data.getTodos().size() + " todos.");
        }
    }
}

