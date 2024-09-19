package com.example.pinteck.service;

import com.example.pinteck.domain.Budget;
import com.example.pinteck.repository.BudgetRepository;
import com.example.pinteck.repository.TransactionRepository;
import com.example.pinteck.domain.Transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service

public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	// 사용자의 예산 목록 조회 메서드
	public List<Budget> getBudgetsByUserId(Long userId) {
		return budgetRepository.findByUserId(userId);  // 사용자 ID를 기준으로 예산 조회
	}

	public Budget createBudget(Budget budget) {
		return budgetRepository.save(budget);
	}

	public void deleteBudget(Long budgetId) {
		budgetRepository.deleteById(budgetId);
	}

	// 예산 초과 여부 확인 메서드
	public boolean isBudgetExceeded(Long userId, Long categoryId) {
		List<Budget> budgets = budgetRepository.findByUserIdAndCategoryId(userId, categoryId);
		if (budgets.isEmpty()) {
			return false;  // 예산이 없을 경우 초과할 수 없음
		}
		Budget budget = budgets.get(0);  // 카테고리별 예산 가져오기
		double totalSpent = transactionRepository
			.findByAccountId(categoryId)
			.stream()
			.mapToDouble(Transaction::getAmount)
			.sum();  // 해당 카테고리에서 사용된 금액 합계

		return totalSpent > budget.getAmount();  // 예산 초과 여부 확인
	}
}
