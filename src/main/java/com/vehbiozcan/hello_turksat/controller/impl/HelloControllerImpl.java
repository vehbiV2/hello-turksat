package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IHelloController;
import com.vehbiozcan.hello_turksat.dto.DtoHello;
import com.vehbiozcan.hello_turksat.exception.BaseException;
import com.vehbiozcan.hello_turksat.exception.ErrorMessage;
import com.vehbiozcan.hello_turksat.exception.MessageType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gorev1")
//Route isimlendirmesi ilk görev olduğundan ve RequestMapping anotasyonunu da kullanmak adına "/gorev1" şeklinde isimlendirildi.

public class HelloControllerImpl implements IHelloController {
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
    @Override
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
    @Override
    public String sayHello(@RequestParam(required = false) String parameter){
        //RequestParam(required=true) anotasyonu ile parametrenin zorunlu olduğunu gösterdik parametre gelmezse 500 yerine 400 kodu dönecek

        if (parameter == null || parameter.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.FIELD_REQUIRED,null));
        }

        //büyük küçük harf duyarsız karşılaştırıyoruz
        if("TÜRKSAT".equalsIgnoreCase(parameter.trim())){
            return "Merhaba TÜRKSAT!";
        }
        return "Gönderilen parametre: " + parameter.trim() ;
    }

    @PostMapping("/json-merhaba")
    @Override
    public String sayHello(@RequestBody DtoHello parameter){
        if (parameter == null || parameter.getMessage().isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.FIELD_REQUIRED,null));
        }
        if("TÜRKSAT".equalsIgnoreCase(parameter.getMessage().trim())){
            return "Merhaba TÜRKSAT!";
        }
        return "Gönderilen parametre: " + parameter.getMessage().trim() ;
    }

}
