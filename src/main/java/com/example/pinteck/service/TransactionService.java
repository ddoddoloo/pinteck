package com.example.pinteck.service;

import com.example.pinteck.domain.Transaction;
import com.example.pinteck.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getTransactionsByAccountId(Long accountId) {
		return transactionRepository.findByAccountId(accountId);
	}

	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public void deleteTransaction(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}
}
