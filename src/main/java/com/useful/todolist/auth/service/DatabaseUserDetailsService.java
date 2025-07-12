package com.useful.todolist.auth.service;

import com.useful.todolist.user.dto.UserDTO;
import com.useful.todolist.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDTO user = userMapper.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userId);
        }
        return User.withUsername(user.getUserName())
                .password(user.getPassword())
                // .roles(user.getRoleId()) // or map to authorities if you have more complex roles
                .build();
    }
}
