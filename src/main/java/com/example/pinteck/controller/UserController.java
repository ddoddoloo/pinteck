package com.example.pinteck.controller;

import com.example.pinteck.domain.User;
import com.example.pinteck.dto.LoginRequest;
import com.example.pinteck.dto.SignupRequest;
import com.example.pinteck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private UserService userService;

	// 회원가입 엔드포인트
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
		User user = userService.signup(request);
		return ResponseEntity.status(201).body(user);
	}

	// 로그인 엔드포인트
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
		String token = userService.login(request);
		return ResponseEntity.ok().body(token);
	}
}
