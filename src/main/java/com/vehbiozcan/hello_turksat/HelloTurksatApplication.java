package com.vehbiozcan.hello_turksat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloTurksatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloTurksatApplication.class, args);
		System.out.println("Merhaba Dünya!");
	}

}
