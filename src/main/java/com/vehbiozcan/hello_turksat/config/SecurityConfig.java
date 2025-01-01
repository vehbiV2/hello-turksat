package com.vehbiozcan.hello_turksat.config;

import com.vehbiozcan.hello_turksat.entity.Role;
import com.vehbiozcan.hello_turksat.jwt.AuthEntryPoint;
import com.vehbiozcan.hello_turksat.jwt.IJwtService;
import com.vehbiozcan.hello_turksat.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // extends WebSecurityConfigurerAdapter {
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/gorev1/**").authenticated() // /gorev1 rotasına sadece doğrulama yapmış kullanıcılar erişebilir
                .anyRequest().permitAll() // belirlediğimiz rota dışındakiler için doğrulama istemez
                .and()
                .httpBasic(); // Base64 HTTP Basic Authentication ekliyoruz
    }*/

    public static final String AUTHENTICATE = "/authenticate";
    public static final String REGISTER = "/register";
    public static final String REFRESH_TOKEN = "/refresh-token";
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/webjars/**"
    };
    public static final String ADMIN = "/admin/**";
    public static final String USER = "/user/**";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeHttpRequests(request ->
                request
                        .antMatchers(AUTHENTICATE, REGISTER, REFRESH_TOKEN).permitAll()
                        .antMatchers(SWAGGER_WHITELIST).permitAll()
                        .antMatchers(ADMIN).hasAuthority(Role.ADMIN.name())
                        .antMatchers(USER).hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                        .anyRequest().authenticated())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
