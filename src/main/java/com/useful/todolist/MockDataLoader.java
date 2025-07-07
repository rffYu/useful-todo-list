package com.useful.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.useful.todolist.todo.dao.TodoItemEntity;
import com.useful.todolist.todo.repository.TodoRepository;
import com.useful.todolist.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.useful.todolist.user.dao.UserEntity;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
class InitialData {
    private List<UserEntity> users;
    private List<TodoItemEntity> todos;
}

@Component
public class MockDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public MockDataLoader(UserRepository userRepository, TodoRepository todoRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
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
            userRepository.deleteAll();

            List<UserEntity> encodedUsers = data.getUsers().stream()
            .peek(user -> {
                user.setPassword(passwordEncoder.encode(user.getPassword())); // 💡 encode password here
            })
            .toList();

            userRepository.saveAll(encodedUsers);
            System.out.println("Loaded " + data.getUsers().size() + " users.");
        }

        if (data.getTodos() != null) {
            todoRepository.deleteAll();
            todoRepository.saveAll(data.getTodos());
            System.out.println("Loaded " + data.getTodos().size() + " todos.");
        }
    }
}

