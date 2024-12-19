package com.vehbiozcan.hello_turksat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/gorev1/**").authenticated() // /gorev1 rotasına sadece doğrulama yapmış kullanıcılar erişebilir
                .anyRequest().permitAll() // belirlediğimiz rota dışındakiler için doğrulama istemez
                .and()
                .httpBasic(); // Base64 HTTP Basic Authentication ekliyoruz
    }
}
