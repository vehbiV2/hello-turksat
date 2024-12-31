package com.vehbiozcan.hello_turksat.service;

import com.vehbiozcan.hello_turksat.dto.DtoUser;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthRequest;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthResponse;
import com.vehbiozcan.hello_turksat.jwt.dto.RefreshTokenRequest;

public interface IAuthService {
    public DtoUser register(AuthRequest authRequest);
    public AuthResponse authenticate(AuthRequest authRequest);
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
