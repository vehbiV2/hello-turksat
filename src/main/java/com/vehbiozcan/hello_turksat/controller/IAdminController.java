package com.vehbiozcan.hello_turksat.controller;

import com.vehbiozcan.hello_turksat.entity.RootEntity;
import org.springframework.http.ResponseEntity;

public interface IAdminController {
    public RootEntity<String> helloAdmin();
}
