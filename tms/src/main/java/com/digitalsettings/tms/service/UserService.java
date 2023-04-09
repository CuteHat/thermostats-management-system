package com.digitalsettings.tms.service;

import com.digitalsettings.tms.persistence.entity.UserEntity;

public interface UserService {

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findById(Long id);
}
