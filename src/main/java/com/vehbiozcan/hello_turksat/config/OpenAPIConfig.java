package com.vehbiozcan.hello_turksat.config;

import com.vehbiozcan.hello_turksat.schema.ApiResponseSchema;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {
    //Swagger üzerinde response türlerimizi dökümante edebilmek için ApiResponselarımızı oluşturduk
    ApiResponse badRequest = new ApiResponse().description("Bad Request").content(
            new Content().addMediaType("application/json",
                    new MediaType().schema(
                            ApiResponseSchema.getCustomResponseSchema("YYYY-MM-DDTHH:mm:ss.SSSZ",
                                    400,"Bad Request","/gorev1/merhaba")))
    );
    ApiResponse notFound = new ApiResponse().description("Not Found").content(
            new Content().addMediaType("application/json",
                                               new MediaType().schema(
                                                       ApiResponseSchema.getCustomResponseSchema("YYYY-MM-DDTHH:mm:ss.SSSZ",
                                                               404,"Not Found","/gorev1/merhaba")))
    );

    ApiResponse internalServerError = new ApiResponse().description("Internal Server Error").content(
            new Content().addMediaType("application/json",
                                               new MediaType().schema(
                                                       ApiResponseSchema.getCustomResponseSchema("YYYY-MM-DDTHH:mm:ss.SSSZ",
                                                               500,"Internal Server Error","/gorev1/merhaba")))
    );



    @Bean
    public OpenAPI customOpenAPI(){

        Components components = new Components();
        //Oluşturduğumuz responselarımızı component üzerine ekliyoruz ve ApiResponse anotasyonlarımızda kullanmak için bir key ekliyoruz
        components.addResponses("badRequest",badRequest);
        components.addResponses("notFound",notFound);
        components.addResponses("internalServerError",internalServerError);

        //Componentimizi de konfigurasyonumuza dahil ediyoruz
        return new OpenAPI().components(components).info(
                new Info()
                        .title("Görev 1: Merhaba TÜRKSAT!")
                        .version("0.0.1-SNAPSHOT")
                        .description("Türksat Aday Mühendislik Görevi 1")
        );
    }

}
