package com.example.implementingspringsecurity.Services;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private String secretKey = "dfdhfhdhfh-dfd-f - f-dfdfdfdfd334994 fd9f9d9f";

    public Key getSigninKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSigninKey())
                .compact();
    }

    public String getusernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
