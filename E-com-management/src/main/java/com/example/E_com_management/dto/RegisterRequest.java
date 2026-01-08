package com.example.E_com_management.dto;

import com.example.E_com_management.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String role;
}
