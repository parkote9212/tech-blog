package com.pgc.techblog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // crzf 보호 비활성화
                .csrf(csrf -> csrf.disable())
//                http 요청에 대한 인가 규칙 설명
                .authorizeHttpRequests(auth -> auth
//                        /** 모든 경로의 모든 요청을 인증없이 허용
                        .requestMatchers("/**").permitAll())
                .build();
    }
}
