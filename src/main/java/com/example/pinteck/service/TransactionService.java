package com.example.pinteck.service;

import com.example.pinteck.domain.Transaction;
import com.example.pinteck.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	// 거래 설명으로 검색
	public List<Transaction> searchTransactionsByDescription(String keyword) {
		return transactionRepository.findByDescriptionContainingIgnoreCase(keyword);
	}

	// 금액 범위로 검색
	public List<Transaction> searchTransactionsByAmountRange(double minAmount, double maxAmount) {
		return transactionRepository.findByAmountBetween(minAmount, maxAmount);
	}

	// 날짜 범위로 검색
	public List<Transaction> searchTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		return transactionRepository.findByTransactionDateBetween(startDate, endDate);
	}
}
