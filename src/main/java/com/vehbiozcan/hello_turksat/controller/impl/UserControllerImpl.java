package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IUserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {

    @GetMapping("/test-user")
    @Override
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello User :D");
    }
}
