package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.config.properties.JwtProperties;
import com.digitalsettings.tms.service.TokenService;
import com.digitalsettings.tms.util.CustomJwtClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final JwtEncoder encoder;
    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(String subject, List<String> scopes) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .issuer(jwtProperties.issuer())
                .audience(List.of(jwtProperties.audience()))
                .subject(subject)
                .claim(CustomJwtClaims.SCOPE.value, String.join(" ", scopes))
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}