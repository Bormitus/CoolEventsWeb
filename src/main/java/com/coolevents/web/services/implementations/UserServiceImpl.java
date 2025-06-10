package com.coolevents.web.services.implementations;

import com.coolevents.web.dtos.RegisterDto;
import com.coolevents.web.models.Role;
import com.coolevents.web.models.UserEntity;
import com.coolevents.web.repositories.RoleRepository;
import com.coolevents.web.repositories.UserRepository;
import com.coolevents.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RegisterDto registerDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userEntity.setEmail(registerDto.getEmail());
        Role userRole = roleRepository.findByName("USER");
        userEntity.setRoles(Arrays.asList(userRole));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
