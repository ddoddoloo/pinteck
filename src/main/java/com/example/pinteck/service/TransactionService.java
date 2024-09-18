package com.example.pinteck.service;

import com.example.pinteck.domain.Transaction;
import com.example.pinteck.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	// 설명으로 검색한 거래 내역을 페이징 처리해서 가져오는 메서드
	public Page<Transaction> searchTransactionsPaged(String keyword, Pageable pageable) {
		return transactionRepository.findByDescriptionContainingIgnoreCase(keyword, pageable);
	}
}
