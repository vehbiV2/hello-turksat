package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IUserController;
import com.vehbiozcan.hello_turksat.entity.RootEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserControllerImpl implements IUserController {

    @GetMapping("/test-user")
    @Override
    public RootEntity<String> helloUser() {
        return RootEntity.ok("Hello User :D");
    }
}
