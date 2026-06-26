package com.recurring_deposit.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recurring_deposit.DTO.LoginRequest;
import com.recurring_deposit.DTO.LoginResponse;
import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Repository.UserRepository;
import com.recurring_deposit.Util.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserEntity register(UserEntity user) {

    String email =
            user.getEmail()
                    .trim()
                    .toLowerCase();

    if (userRepository.existsByEmail(email)) {

        throw new IllegalArgumentException(
                "Email already exists");
    }

    if (userRepository.existsByMobileNumber(
            user.getMobileNumber())) {

        throw new IllegalArgumentException(
                "Mobile number already exists");
    }

    if (user.getRole() == null) {

        throw new IllegalArgumentException(
                "Role is required");
    }

    if (user.getPassword() == null
            || user.getPassword().length() < 8) {

        throw new IllegalArgumentException(
                "Password must contain at least 8 characters");
    }

    user.setEmail(email);

    user.setPassword(
            passwordEncoder.encode(
                    user.getPassword()));

    return userRepository.save(user);
}

    public LoginResponse login(
            LoginRequest request) {

        UserEntity user =
                userRepository.findByEmail(
                        request.getEmail()
                                .trim()
                                .toLowerCase())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid email or password");
        }

        if (Boolean.FALSE.equals(
                user.getActive())) {

            throw new RuntimeException(
                    "Account is inactive");
        }

        String token =
                jwtUtil.generateToken(
                        user.getEmail());

        return new LoginResponse(
                "Login Successful",
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobileNumber(),
                user.getRole().name(),
                user.getActive(),
                user.getProfileCompleted(),
                user.getCreatedAt(),
                token
        );
    }
}