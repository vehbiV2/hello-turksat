package com.vehbiozcan.hello_turksat.jwt;

import com.vehbiozcan.hello_turksat.entity.RefreshToken;
import com.vehbiozcan.hello_turksat.entity.User;

public interface IRefreshTokenService {
    public RefreshToken createRefreshToken(User user);
    public RefreshToken saveRefreshToken(RefreshToken refreshToken);
    public RefreshToken findByRefreshToken(String refreshToken);
    public boolean isExpireRefreshToken(RefreshToken refreshToken);
}
