package com.vehbiozcan.hello_turksat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gorev1")
//Route isimlendirmesi ilk görev olduğundan ve RequestMapping anotasyonunu da kullanmak adına "/gorev1" şeklinde isimlendirildi.

public class HelloController {
    //Operation Anotasyonu içerisinde Servisin tanımını yapıyoruz ve response
    @GetMapping("/merhaba")
    @Operation(
            description = "GET Hello Service",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
            }
    )
    public String getHello(){
        return "Merhaba Dünya!";
    }

    @PostMapping("/merhaba")
    @Operation(
            description = "POST Hello Service",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "404", ref = "notFound"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
            }
    )


    public String sayHello(@RequestParam(required = true) String parameter){
        //RequestParam(required=true) anotasyonu ile parametrenin zorunlu olduğunu gösterdik parametre gelmezse 500 yerine 400 kodu dönecek

        //büyük küçük harf duyarsız karşılaştırıyoruz
        if("TÜRKSAT".equalsIgnoreCase(parameter.trim())){
            return "Merhaba TÜRKSAT!";
        }
        return "Gönderilen parametre: " + parameter.trim() ;
    }

}
