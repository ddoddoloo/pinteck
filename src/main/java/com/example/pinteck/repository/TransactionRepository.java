package com.example.pinteck.repository;

import com.example.pinteck.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 예산 초과 여부를 확인하기 위해 거래 내역 조회

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// 거래 설명으로 지출 내역 검색
	List<Transaction> findByDescriptionContainingIgnoreCase(String keyword);

	// 계좌 ID로 거래 검색
	List<Transaction> findByAccountId(Long accountId);

	// 거래 금액 범위로 지출 내역 검색
	List<Transaction> findByAmountBetween(double minAmount, double maxAmount);

	// 거래 날짜 범위로 지출 내역 검색
	List<Transaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	// 설명으로 검색한 결과를 페이징
	Page<Transaction> findByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);
}
