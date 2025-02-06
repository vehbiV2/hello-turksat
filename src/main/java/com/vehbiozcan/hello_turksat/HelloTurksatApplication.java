package com.vehbiozcan.hello_turksat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HelloTurksatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloTurksatApplication.class, args);
		System.out.println("Merhaba Dünya!");
	}

}
