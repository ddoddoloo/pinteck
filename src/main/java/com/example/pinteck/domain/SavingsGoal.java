package com.example.pinteck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "savings_goal")
public class SavingsGoal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goalId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	private String goalName;  // 저축 목표 이름
	private double targetAmount;  // 목표 금액
	private double currentAmount;  // 현재 저축된 금액
	private LocalDateTime dueDate;  // 목표 달성 기한

}
