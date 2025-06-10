package com.coolevents.web.repositories;

import com.coolevents.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findFirstByUsername(String username);
}
