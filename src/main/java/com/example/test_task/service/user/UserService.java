package com.example.test_task.service.user;

import com.example.test_task.dto.auth.AuthRequestDto;
import com.example.test_task.model.User;

import java.util.List;

public interface UserService {

    User register(AuthRequestDto user);

    List<User> getAll();

    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);
}
