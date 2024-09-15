package com.example.pinteck.repository;

import com.example.pinteck.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// 거래 설명으로 지출 내역 검색
	List<Transaction> findByDescriptionContainingIgnoreCase(String keyword);

	// 거래 금액 범위로 지출 내역 검색
	List<Transaction> findByAmountBetween(double minAmount, double maxAmount);

	// 거래 날짜 범위로 지출 내역 검색
	List<Transaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
