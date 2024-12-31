package com.vehbiozcan.hello_turksat.dto;

import com.vehbiozcan.hello_turksat.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {
    private Long id;
    private String username;
    private Role role;
}
