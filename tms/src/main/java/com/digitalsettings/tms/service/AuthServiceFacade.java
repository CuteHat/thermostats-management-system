package com.digitalsettings.tms.service;

import com.digitalsettings.tms.model.AuthResponse;
import com.digitalsettings.tms.model.TokenRequest;

public interface AuthServiceFacade {
    AuthResponse getToken(TokenRequest request);
}
