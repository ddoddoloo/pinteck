package com.example.pinteck.controller;

import com.example.pinteck.domain.Account;
import com.example.pinteck.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Account>> getAccountsByUser(@PathVariable Long userId) {
		List<Account> accounts = accountService.getAccountsByUserId(userId);
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/{accountId}")
	public ResponseEntity<Optional<Account>> getAccountById(@PathVariable Long accountId) {
		Optional<Account> account = accountService.getAccountById(accountId);
		return ResponseEntity.ok(account);
	}

	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account createdAccount = accountService.createAccount(account);
		return ResponseEntity.ok(createdAccount);
	}

	@DeleteMapping("/{accountId}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
		accountService.deleteAccount(accountId);
		return ResponseEntity.noContent().build();
	}
}
