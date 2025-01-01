package com.vehbiozcan.hello_turksat.controller;

import com.vehbiozcan.hello_turksat.dto.DtoHello;

public interface IHelloController {
    public String getHello();
    public String sayHello(String parameter);
    public String sayHello(DtoHello parameter);
}
