package com.coolevents.web.services;

import com.coolevents.web.dtos.RegisterDto;
import com.coolevents.web.models.UserEntity;
import jakarta.validation.constraints.NotBlank;

public interface UserService {
    void registerUser(RegisterDto registerDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
