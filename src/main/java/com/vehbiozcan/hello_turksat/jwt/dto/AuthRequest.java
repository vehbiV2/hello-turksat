package com.vehbiozcan.hello_turksat.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotNull(message = "İsim alanı null olamaz!")
    @NotEmpty(message = "Username alanı boş bırakılamaz!")
    @Size(min = 6, max = 30, message = "Username 6 ile 30 karakter arasında olmalıdır.")
    private String username;

    @Size(min = 6, message = "Password alanı en az 6 karakter olmalıdır.")
    private String password;
}
