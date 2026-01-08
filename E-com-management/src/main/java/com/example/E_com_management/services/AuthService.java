package com.example.E_com_management.services;

import com.example.E_com_management.dto.AuthResponse;
import com.example.E_com_management.models.User;
import com.example.E_com_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.E_com_management.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.E_com_management.dto.RegisterRequest;
import com.example.E_com_management.enums.Role;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new AuthResponse(jwtUtil.generateAccessToken(email), jwtUtil.generateRefreshToken(email));

    }

    public AuthResponse refreshToken(String refreshToken) {
        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh Token");
        }
        String email = jwtUtil.extractEmail(refreshToken);
        return new AuthResponse(jwtUtil.generateAccessToken(email), refreshToken);
    }

    public String register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Convert string from request to enum
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (Exception e) {
            role = Role.CUSTOMER; // default if invalid
        }
        user.setRole(role);

        userRepository.save(user);
        return "User Registered";
    }

}
