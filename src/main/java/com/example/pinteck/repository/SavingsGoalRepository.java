package com.example.pinteck.repository;

import com.example.pinteck.domain.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
	List<SavingsGoal> findByUserId(Long userId);  // 사용자별 저축 목표 조회
}
