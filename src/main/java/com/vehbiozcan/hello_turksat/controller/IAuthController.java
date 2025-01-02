package com.vehbiozcan.hello_turksat.controller;

import com.vehbiozcan.hello_turksat.dto.DtoUser;
import com.vehbiozcan.hello_turksat.entity.RootEntity;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthRequest;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthResponse;
import com.vehbiozcan.hello_turksat.jwt.dto.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<RootEntity<DtoUser>> register(AuthRequest authRequest);
    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest);
    public ResponseEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
