package com.example.pinteck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@ManyToOne
	@JoinColumn(name = "accountId", nullable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;

	private double amount;
	private LocalDateTime transactionDate;
	private String description;
}
