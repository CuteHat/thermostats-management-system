package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.persistence.entity.UserEntity;
import com.digitalsettings.tms.persistence.repository.UserRepository;
import com.digitalsettings.tms.service.UserService;
import com.digitalsettings.tms.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> HandledExceptionFactory.getNotFoundException("User not found"));
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {
        UserEntity user = findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw HandledExceptionFactory.getUnauthorizedException("Invalid credentials");
        return user;
    }

    @Override
    public UserEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> HandledExceptionFactory.getNotFoundException("User not found"));
    }

}