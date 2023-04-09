package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.model.AuthResponse;
import com.digitalsettings.tms.model.TokenRequest;
import com.digitalsettings.tms.persistence.entity.ScopeEntity;
import com.digitalsettings.tms.persistence.entity.UserEntity;
import com.digitalsettings.tms.service.AuthServiceFacade;
import com.digitalsettings.tms.service.TokenService;
import com.digitalsettings.tms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceFacadeImpl implements AuthServiceFacade {
    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public AuthResponse getToken(TokenRequest request) {
        UserEntity user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return getAuthResponse(user);
    }

    private AuthResponse getAuthResponse(UserEntity user) {
        String sub = user.getId().toString();
        Collection<ScopeEntity> scopes = user.getScopes();
        return AuthResponse.builder()
                .accessToken(tokenService.generateAccessToken(sub, scopes.stream().map(scopeEntity -> scopeEntity.getName().name()).toList()))
                .build();
    }

}
