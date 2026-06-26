package com.recurring_deposit.Controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.DTO.LoginRequest;
import com.recurring_deposit.DTO.LoginResponse;
import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    /*
     * REGISTER USER
     */
    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(
            @Valid @RequestBody UserEntity user) {

        UserEntity savedUser =
                authService.register(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    /*
     * LOGIN USER
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }
}