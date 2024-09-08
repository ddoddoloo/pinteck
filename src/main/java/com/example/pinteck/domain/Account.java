package com.example.pinteck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	private String accountType;
	private String accountNumber;
	private String bankName;
	private double balance;

	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt = LocalDateTime.now();

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Transaction> transactions;

}
