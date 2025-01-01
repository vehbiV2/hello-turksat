package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IAdminController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements IAdminController {

    @GetMapping("/test-admin")
    @Override
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello Admin :D");
    }
}
