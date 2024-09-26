package com.example.shop.dto.user;

import com.example.shop.models.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        UserRole role
) {}
