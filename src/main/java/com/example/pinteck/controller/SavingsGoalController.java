package com.example.pinteck.controller;

import com.example.pinteck.domain.SavingsGoal;
import com.example.pinteck.service.SavingsGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savings-goals")
public class SavingsGoalController {

	@Autowired
	private SavingsGoalService savingsGoalService;

	// 사용자별 저축 목표 조회
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<SavingsGoal>> getSavingsGoalsByUser(@PathVariable Long userId) {
		List<SavingsGoal> savingsGoals = savingsGoalService.getSavingsGoalsByUserId(userId);
		return ResponseEntity.ok(savingsGoals);
	}

	// 저축 목표 생성
	@PostMapping("/create")
	public ResponseEntity<SavingsGoal> createSavingsGoal(@RequestBody SavingsGoal savingsGoal) {
		SavingsGoal createdGoal = savingsGoalService.createSavingsGoal(savingsGoal);
		return ResponseEntity.ok(createdGoal);
	}

	// 저축 목표 삭제
	@DeleteMapping("/{goalId}")
	public ResponseEntity<Void> deleteSavingsGoal(@PathVariable Long goalId) {
		savingsGoalService.deleteSavingsGoal(goalId);
		return ResponseEntity.noContent().build();
	}

	// 저축 목표 달성 여부 확인
	@GetMapping("/status/{goalId}")
	public ResponseEntity<Boolean> checkGoalStatus(@PathVariable Long goalId) {
		SavingsGoal goal = savingsGoalService.getSavingsGoalsByUserId(goalId).get(0);
		boolean isAchieved = savingsGoalService.isGoalAchieved(goal);
		return ResponseEntity.ok(isAchieved);
	}
}
