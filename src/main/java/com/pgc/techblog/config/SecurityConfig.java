package com.pgc.techblog.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // crzf 보호 비활성화
                .csrf(csrf -> csrf.disable())
//                http 요청에 대한 인가 규칙 설명
                .authorizeHttpRequests(auth -> auth
//                      // '/api/members/signup', '/api/members/login'은 인증 없이 허용
                        .requestMatchers("/api/members/signup","/api/members/login").permitAll()
//                        나머지 모든 요청은 인증
                        .anyRequest().authenticated()
                )

//                우리가 만든 jwt 필터를 Spring Security 필터 체인에 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();



    }
}
