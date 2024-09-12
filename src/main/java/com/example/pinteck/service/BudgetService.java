package com.example.pinteck.service;

import com.example.pinteck.domain.Budget;
import com.example.pinteck.domain.Transaction;
import com.example.pinteck.repository.BudgetRepository;
import com.example.pinteck.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private TransactionRepository transactionRepository;

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
