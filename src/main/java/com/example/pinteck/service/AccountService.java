package com.example.pinteck.service;

import com.example.pinteck.domain.Account;
import com.example.pinteck.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAccountsByUserId(Long userId) {
		return accountRepository.findByUserId(userId);
	}

	public Optional<Account> getAccountById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public void deleteAccount(Long accountId) {
		accountRepository.deleteById(accountId);
	}
}
