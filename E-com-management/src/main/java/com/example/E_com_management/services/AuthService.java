package com.example.E_com_management.services;

import com.example.E_com_management.dto.AuthResponse;
import com.example.E_com_management.models.User;
import com.example.E_com_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.E_com_management.security.JwtUtil;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
