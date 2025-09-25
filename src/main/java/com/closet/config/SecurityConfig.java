package com.closet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 개발용
                .headers(headers -> headers
                        .frameOptions(frame -> frame
                                .sameOrigin() // H2 콘솔 iframe 허용
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 접근 허용
                        .requestMatchers("/cheat/check").permitAll() // 사기 이력 조회
                        .requestMatchers("/board/**").permitAll()  // ✅ 테스트용
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll()) // formLogin 최신 DSL
                .httpBasic(httpBasic -> httpBasic.disable()); // 필요하면 HTTP Basic 사용 안 함
        return http.build();
    }

    // 테스트용 유저
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("test")
                .password("1234")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}