package com.example.pinteck.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank(message = "사용자 이름은 필수 항목입니다.")
	private String username;

	@NotBlank(message = "비밀번호는 필수 항목입니다.")
	private String password;

	// Getters and Setters
}
