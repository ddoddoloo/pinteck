package com.example.pinteck.controller;

import com.example.pinteck.domain.Transaction;
import com.example.pinteck.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	// 거래 설명으로 검색
	@GetMapping("/search/description")
	public ResponseEntity<List<Transaction>> searchTransactionsByDescription(@RequestParam String keyword) {
		List<Transaction> transactions = transactionService.searchTransactionsByDescription(keyword);
		return ResponseEntity.ok(transactions);
	}

	// 금액 범위로 검색
	@GetMapping("/search/amount")
	public ResponseEntity<List<Transaction>> searchTransactionsByAmount(@RequestParam double minAmount,
		@RequestParam double maxAmount) {
		List<Transaction> transactions = transactionService.searchTransactionsByAmountRange(minAmount, maxAmount);
		return ResponseEntity.ok(transactions);
	}

	// 날짜 범위로 검색
	@GetMapping("/search/date")
	public ResponseEntity<List<Transaction>> searchTransactionsByDate(@RequestParam String startDate,
		@RequestParam String endDate) {
		LocalDateTime start = LocalDateTime.parse(startDate);
		LocalDateTime end = LocalDateTime.parse(endDate);
		List<Transaction> transactions = transactionService.searchTransactionsByDateRange(start, end);
		return ResponseEntity.ok(transactions);
	}
}
