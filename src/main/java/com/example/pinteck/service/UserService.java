package com.example.pinteck.service;

import com.example.pinteck.domain.User;
import com.example.pinteck.dto.LoginRequest;
import com.example.pinteck.dto.SignupRequest;
import com.example.pinteck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User signup(SignupRequest request) {
		// 사용자 이름 중복 체크
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new RuntimeException("이미 존재하는 사용자입니다.");
		}

		// 비밀번호 암호화 및 사용자 저장
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());

		return userRepository.save(user);
	}

	public String login(LoginRequest request) {
		Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			// 비밀번호 검사
			if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				// JWT 토큰 생성 로직 추가 필요
				return "JWT_TOKEN_EXAMPLE";
			}
		}
		throw new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다.");
	}
}
