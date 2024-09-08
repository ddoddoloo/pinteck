package com.example.pinteck.controller;

import com.example.pinteck.domain.Transaction;
import com.example.pinteck.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable Long accountId) {
		List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
		return ResponseEntity.ok(transactions);
	}

	@PostMapping("/create")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		Transaction createdTransaction = transactionService.createTransaction(transaction);
		return ResponseEntity.ok(createdTransaction);
	}

	@DeleteMapping("/{transactionId}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
		transactionService.deleteTransaction(transactionId);
		return ResponseEntity.noContent().build();
	}
}
