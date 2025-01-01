package com.vehbiozcan.hello_turksat.controller;

import com.vehbiozcan.hello_turksat.entity.RootEntity;
import org.springframework.http.ResponseEntity;

public interface IUserController {
    public RootEntity<String> helloUser();
}
