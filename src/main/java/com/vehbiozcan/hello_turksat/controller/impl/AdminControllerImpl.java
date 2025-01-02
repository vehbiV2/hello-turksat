package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IAdminController;
import com.vehbiozcan.hello_turksat.entity.RootEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminControllerImpl implements IAdminController {

    @GetMapping("/test-admin")
    @Override
    public RootEntity<String> helloAdmin() {
        return RootEntity.ok("Hello Admin :D");
    }
}
