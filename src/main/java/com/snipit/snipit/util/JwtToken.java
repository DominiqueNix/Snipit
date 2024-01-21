package com.snipit.snipit.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtToken {
    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(UserDetails user) {
        long now = new Date().getTime();
        Date expirationTime = new Date(now + expiration);
        return Jwts.builder()
                .setSubject(String.format("%s", user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch(ExpiredJwtException | MalformedJwtException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
