package com.example.shop.dto.user;

import com.example.shop.models.UserModel;

public record UserResponseDTO(String id, String name, String email, String role, String token) {

    public static UserResponseDTO forLogin(UserModel user, String token) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole().name(), token);
    }

    public static UserResponseDTO forRegister(UserModel user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole().name(), null);
    }
}
