package com.example.test_task.controller;

import com.example.test_task.dto.auth.AuthRequestDto;
import com.example.test_task.dto.auth.AuthResponseDto;
import com.example.test_task.security.jwt.JwtTokenProvider;
import com.example.test_task.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/auth/")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody AuthRequestDto request) {
        try {
            userService.register(request);

            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            String token = jwtTokenProvider.createToken(email);
            AuthResponseDto response = AuthResponseDto.builder().email(email).token(token).build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequestDto request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            String token = jwtTokenProvider.createToken(email);
            AuthResponseDto response = AuthResponseDto.builder().email(email).token(token).build();

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }
}
