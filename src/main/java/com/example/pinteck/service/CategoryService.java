package com.example.pinteck.service;

import com.example.pinteck.domain.Category;
import com.example.pinteck.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Repository
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
}
