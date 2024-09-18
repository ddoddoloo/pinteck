package com.example.pinteck.controller;

import com.example.pinteck.domain.Transaction;
import com.example.pinteck.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	// 거래 내역과 설명으로 검색
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

	// 설명으로 검색한 거래 내역을 페이징 처리해서 가져오는 메서드
	@GetMapping("/search/paged")
	public ResponseEntity<Page<Transaction>> searchTransactionsPaged(
		@RequestParam String keyword,
		@RequestParam int page,
		@RequestParam int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Transaction> transactions = transactionService.searchTransactionsPaged(keyword, pageable);
		return ResponseEntity.ok(transactions);
	}

}
