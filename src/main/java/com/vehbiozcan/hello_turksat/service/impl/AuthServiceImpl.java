package com.vehbiozcan.hello_turksat.service.impl;

import com.vehbiozcan.hello_turksat.dto.DtoUser;
import com.vehbiozcan.hello_turksat.entity.RefreshToken;
import com.vehbiozcan.hello_turksat.entity.Role;
import com.vehbiozcan.hello_turksat.entity.User;
import com.vehbiozcan.hello_turksat.exception.BaseException;
import com.vehbiozcan.hello_turksat.exception.ErrorMessage;
import com.vehbiozcan.hello_turksat.exception.MessageType;
import com.vehbiozcan.hello_turksat.jwt.IJwtService;
import com.vehbiozcan.hello_turksat.jwt.IRefreshTokenService;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthRequest;
import com.vehbiozcan.hello_turksat.jwt.dto.AuthResponse;
import com.vehbiozcan.hello_turksat.jwt.dto.RefreshTokenRequest;
import com.vehbiozcan.hello_turksat.repository.UserRepository;
import com.vehbiozcan.hello_turksat.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User user = new User();
        if(userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            throw new BaseException(new ErrorMessage(MessageType.USER_ALREADY_EXISTS));
        }
        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, dtoUser);
        dtoUser.setRole(savedUser.getRole());

        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

       try {
           UsernamePasswordAuthenticationToken authentication =
                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

           /// Bu kısım zaten kullanıcı yoksa loadByUsername kısmında ovverride ettiğimiz yerden hatayı fırlatır
           authenticationProvider.authenticate(authentication);

           Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());
           User user = optionalUser.get();

           String accessToken = jwtService.generateToken(user);

           RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
           RefreshToken dbRefreshToken = refreshTokenService.saveRefreshToken(refreshToken);

           return new AuthResponse(accessToken,dbRefreshToken.getRefreshToken());

       } catch (InternalAuthenticationServiceException e) {
           throw new BaseException(new ErrorMessage(MessageType.VALIDATION_ERROR,": [ " + e.getMessage() + " ]"));
       } catch (BaseException e) {
           throw new BaseException(new ErrorMessage(e.getMessageType()));
       }catch (BadCredentialsException e){
           throw new BaseException(new ErrorMessage(MessageType.PASSWORD_WRONG));
       }
       catch (Exception e) {
           System.out.println(e.getClass());
           throw new BaseException(new ErrorMessage(MessageType.INTERNAL_SERVER_ERROR));
       }
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        if(refreshTokenRequest.getRefreshToken() == null || refreshTokenRequest.getRefreshToken().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NULL));
        }

        RefreshToken refreshToken = refreshTokenService.findByRefreshToken(refreshTokenRequest.getRefreshToken());

        if (refreshToken == null) {
            //System.out.println("REFRESH TOKEN IS NULL" + refreshTokenRequest.getRefreshToken());
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND));
        }

        if (refreshTokenService.isExpireRefreshToken(refreshToken)) {
            //System.out.println("REFRESH TOKEN IS EXPIRED" + refreshToken.getRefreshToken());
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_EXPIRED));
        }

        String accessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken dbRefreshToken = refreshTokenService.saveRefreshToken(
                refreshTokenService.createRefreshToken(refreshToken.getUser())
        );

        return new AuthResponse(accessToken,dbRefreshToken.getRefreshToken());
    }

}
