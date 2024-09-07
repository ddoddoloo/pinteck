package com.example.pinteck.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {

	@NotBlank(message = "사용자 이름은 필수 항목입니다.")
	private String username;

	@NotBlank(message = "비밀번호는 필수 항목입니다.")
	private String password;

	@Email(message = "유효한 이메일 주소를 입력하세요.")
	private String email;

	// Getters and Setters
}
