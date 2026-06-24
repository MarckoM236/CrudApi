package com.marco.crudapi.Auth.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marco.crudapi.User.Entity.User;

import io.jsonwebtoken.Claims;
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

    //generate signature
    private SecretKey getSigningKey(){
       return Keys.hmacShaKeyFor(
            secretKey.getBytes(
                StandardCharsets.UTF_8
            )
        );
    }

    //extract data token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //get info user (email)
    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    //validate expiration
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //valida token (user valid and not expiration)
    public boolean isTokenValid(String token, String userEmail){
        try {
            String tokenUserEmail = extractUsername(token);
            return (tokenUserEmail.equals(userEmail) && !isTokenExpired(token));
            
        } catch (Exception e) {
            return false;
        }
    }

}
