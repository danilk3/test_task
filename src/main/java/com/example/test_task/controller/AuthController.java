package com.example.test_task.controller;

import com.example.test_task.dto.auth.AuthRequestDto;
import com.example.test_task.dto.auth.AuthResponseDto;
import com.example.test_task.security.jwt.JwtTokenProvider;
import com.example.test_task.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthRequestDto request) {
        try {
            userService.register(request);

            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            // TODO: красивый эксепшн.
            String token = jwtTokenProvider.createToken(email);
            AuthResponseDto response = AuthResponseDto.builder().email(email).token(token).build();

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // TODO: переделать
            throw new BadCredentialsException("Invalid request");
        }
    }

    @GetMapping("all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.getAll());
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            // TODO: красивый эксепшн.
            String token = jwtTokenProvider.createToken(email);
            AuthResponseDto response = AuthResponseDto.builder().email(email).token(token).build();

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // TODO: переделать
            throw new BadCredentialsException("Invalid request");
        }
    }
}
