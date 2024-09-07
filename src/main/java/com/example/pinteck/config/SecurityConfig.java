package com.example.pinteck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()  // 인증 없이 접근 가능한 경로
				.anyRequest().authenticated()  // 나머지 요청은 인증 필요
			)
			.httpBasic(httpBasic -> {});  // HTTP Basic 인증 활성화

		return http.build();
	}
}
