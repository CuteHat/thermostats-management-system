package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.model.UserDto;
import com.digitalsettings.tms.persistence.entity.UserEntity;
import com.digitalsettings.tms.service.UserService;
import com.digitalsettings.tms.service.UserServiceFacade;
import com.digitalsettings.tms.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceFacadeImpl implements UserServiceFacade {
    private final UserService userService;

    @Override
    public UserDto get() {
        UserEntity user = userService.findById(SecurityUtils.getAuthenticatedUserId());
        return UserDto.from(user);
    }

}
