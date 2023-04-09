package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.UserDto;
import com.digitalsettings.tms.service.UserServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceFacade service;

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto user = service.get();
        return ResponseEntity.ok(user);
    }

}
