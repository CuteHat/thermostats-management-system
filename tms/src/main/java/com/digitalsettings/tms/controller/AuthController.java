package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.AuthResponse;
import com.digitalsettings.tms.model.TokenRequest;
import com.digitalsettings.tms.service.AuthServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceFacade service;

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody TokenRequest request) {
        AuthResponse authResponse = service.getToken(request);
        return ResponseEntity.ok(authResponse);
    }
}