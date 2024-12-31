/*
package com.vehbiozcan.hello_turksat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Base64;

@Configuration
public class UserConfig {

    @Bean
    protected UserDetailsService userDetailsService() {
        // Kullanıcı adı ve şifreyi bellek üzerinden tanımlıyoruz. Spring Security ilk aşamada burayı kontrol edecek
        return new InMemoryUserDetailsManager(
                User.withUsername("testKullanici")
                        .password(passwordEncoder().encode("testSifre")) //
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
*/
