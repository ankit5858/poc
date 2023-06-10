package com.demo.security;


import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.demo.dto.TokenDTO;

@Component
public class TokenGenerator {
    @Autowired
    JwtEncoder accessTokenEncoder;

    @Autowired
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder refreshTokenEncoder;

    private String createAccessToken(Authentication authentication, String authority) {
        var user = (UserDetails) authentication.getPrincipal();
        var now = Instant.now();

        var claimsSet = JwtClaimsSet.builder()
                .issuer("sampleApp")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                .subject(user.getUsername())
                .claim("base", authority)
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken(Authentication authentication, String authority) {
        var user = (UserDetails) authentication.getPrincipal();
        var now = Instant.now();

        var claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .subject(user.getUsername())
                .claim("base", authority)
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public TokenDTO createToken(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof UserDetails user)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
            );
        }

        var ga = user.getAuthorities().stream().findFirst();
        var sga = (SimpleGrantedAuthority)ga.get();
        
        var tokenDTO = new TokenDTO();
        tokenDTO.setUserName(user.getUsername());
        tokenDTO.setAccessToken(createAccessToken(authentication, sga.getAuthority()));

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
        	var now = Instant.now();
        	var expiresAt = jwt.getExpiresAt();
        	var duration = Duration.between(now, expiresAt);
        	var daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken(authentication, sga.getAuthority());
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication, sga.getAuthority());
        }
        tokenDTO.setRefreshToken(refreshToken);

        return tokenDTO;
    }

}
