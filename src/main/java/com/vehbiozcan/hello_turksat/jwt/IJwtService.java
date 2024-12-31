package com.vehbiozcan.hello_turksat.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.function.Function;

public interface IJwtService {
    public String generateToken(UserDetails userDetails);
    public Claims getClaimsFromToken(String token);
    public <T> T exportToken(String token, Function<Claims, T> claimsResolver);
    public String getUsernameByToken(String token);
    public boolean isTokenExpired(String token);
    public boolean isTokenValid(String token, String username);
    public Key getKey();

}
