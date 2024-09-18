package com.example.pinteck.controller;

import com.example.pinteck.domain.Category;
import com.example.pinteck.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category createdCategory = categoryService.createCategory(category);
		return ResponseEntity.ok(createdCategory);
	}
}
