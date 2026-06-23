package com.marco.crudapi.Auth.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marco.crudapi.User.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public JwtService() {}

    public String generateToken(Optional<User> user){
        
        return Jwts.builder()
            .subject(user.get().getEmail())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis()
                    + expiration
                )
            )
            .signWith(
                getSigningKey()
            )
            .compact();
    }

    private SecretKey getSigningKey(){
       return Keys.hmacShaKeyFor(
            secretKey.getBytes(
                StandardCharsets.UTF_8
            )
        );
    }

    public String extractUsername(String token){
        return "";
    }

    public boolean isTokenValid(String token){
        return true;
    }

}
