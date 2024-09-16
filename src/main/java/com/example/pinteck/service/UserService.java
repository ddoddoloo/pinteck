package com.example.pinteck.service;

import com.example.pinteck.domain.User;
import com.example.pinteck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;

	public User registerUser(String username, String password, String email) {
		if (userRepository.findByUsername(username).isPresent()) {
			throw new RuntimeException("이미 존재하는 사용자입니다.");
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
		user.setEmail(email);
		return userRepository.save(user);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
