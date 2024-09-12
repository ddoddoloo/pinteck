package com.example.pinteck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "budget")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long budgetId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;  // 예산은 특정 카테고리에 속함

	private double amount;  // 예산 금액
	private LocalDateTime startDate;  // 예산 시작일
	private LocalDateTime endDate;    // 예산 종료일

}
