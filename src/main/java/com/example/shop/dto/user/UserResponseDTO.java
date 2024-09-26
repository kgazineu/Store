package com.example.shop.dto.user;

import com.example.shop.models.UserModel;

public record UserResponseDTO(String id, String name, String email, String role) {
//    public UserResponseDTO(UserModel user) {
//        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
//    }

}
