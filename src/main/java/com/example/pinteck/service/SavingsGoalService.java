package com.example.pinteck.service;

import com.example.pinteck.domain.SavingsGoal;
import com.example.pinteck.repository.SavingsGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingsGoalService {

	@Autowired
	private SavingsGoalRepository savingsGoalRepository;

	// 사용자별 저축 목표 조회
	public List<SavingsGoal> getSavingsGoalsByUserId(Long userId) {
		return savingsGoalRepository.findByUserId(userId);
	}

	// 저축 목표 생성
	public SavingsGoal createSavingsGoal(SavingsGoal savingsGoal) {
		return savingsGoalRepository.save(savingsGoal);
	}

	// 저축 목표 삭제
	public void deleteSavingsGoal(Long goalId) {
		savingsGoalRepository.deleteById(goalId);
	}

	// 저축 목표 진행률 계산
	public boolean isGoalAchieved(SavingsGoal goal) {
		return goal.getCurrentAmount() >= goal.getTargetAmount();
	}
}
