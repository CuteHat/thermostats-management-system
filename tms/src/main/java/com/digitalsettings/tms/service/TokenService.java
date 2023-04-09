package com.digitalsettings.tms.service;

import java.util.List;

public interface TokenService {
    String generateAccessToken(String subject, List<String> scopes);
}
