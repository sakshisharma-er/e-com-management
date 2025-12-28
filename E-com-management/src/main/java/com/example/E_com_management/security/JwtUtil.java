package com.example.E_com_management.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtUtil {
    private final String SECRET ="ecom_secret_key_123";
    private final long ACCESS_TOKEN_TIME = 15*60*1000;
    private final long REFRESH_TOKEN_TIME = 7*24*60*60*1000;
    public String generateAccessToken(String email){
        return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+ACCESS_TOKEN_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
    }
    public String generateRefreshToken(String email){
        return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+REFRESH_TOKEN_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
    }
    public String extractEmail(String token){
        return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }
    public boolean isTokenValid(String Token){
        try{
            extractEmail(Token);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
