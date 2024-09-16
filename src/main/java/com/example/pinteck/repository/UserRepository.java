package com.example.pinteck.repository;

import com.example.pinteck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// 사용자명을 통해 사용자 정보를 찾는 메서드
	Optional<User> findByUsername(String username);

	// 이메일을 통해 사용자 정보를 찾는 메서드
	Optional<User> findByEmail(String email);
}
