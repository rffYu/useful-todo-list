package com.useful.todolist.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        String currentUsername = authentication.getName();
        return "Hello, you are " + currentUsername;
    }
}
