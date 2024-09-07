package com.example.pinteck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 사용자 인증에 대한 설정 (예: 사용자 세부 정보 서비스 설정 등)

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/auth/**", "/swagger-ui/**", "/v2/api-docs/**").permitAll()
			.anyRequest().authenticated();
		// JWT 인증 필터 추가 필요
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 사용자 인증 매니저 설정
	}
}
