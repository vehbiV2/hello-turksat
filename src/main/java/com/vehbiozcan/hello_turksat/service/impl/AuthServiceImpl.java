package com.vehbiozcan.hello_turksat.service.impl;

import com.vehbiozcan.hello_turksat.dto.DtoUser;
import com.vehbiozcan.hello_turksat.entity.User;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthRequest;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthResponse;
import com.vehbiozcan.hello_turksat.jwt.dto.RefreshTokenRequest;
import com.vehbiozcan.hello_turksat.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User user = new User();

        user.setUsername(authRequest.getUsername());
        //user.setPassword();

        return null;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        return null;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }
}
