package com.example.pinteck.controller;

import com.example.pinteck.domain.Budget;
import com.example.pinteck.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Budget>> getBudgetsByUser(@PathVariable Long userId) {
		List<Budget> budgets = budgetService.getBudgetsByUserId(userId);
		return ResponseEntity.ok(budgets);
	}

	@PostMapping("/create")
	public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
		Budget createdBudget = budgetService.createBudget(budget);
		return ResponseEntity.ok(createdBudget);
	}

	@DeleteMapping("/{budgetId}")
	public ResponseEntity<Void> deleteBudget(@PathVariable Long budgetId) {
		budgetService.deleteBudget(budgetId);
		return ResponseEntity.noContent().build();
	}
}
