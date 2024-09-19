package com.example.pinteck.repository;

import com.example.pinteck.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BudgetRepository extends JpaRepository<Budget, Long> {
	List<Budget> findByUserId(Long userId);  // 특정 사용자의 예산 목록을 조회
	List<Budget> findByUserIdAndCategoryId(Long userId, Long categoryId);  // 특정 카테고리의 예산 조회
}
