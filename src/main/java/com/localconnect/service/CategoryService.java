package com.localconnect.service;

import com.localconnect.entity.Category;
import com.localconnect.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Add category - Admin only
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get all categories - Everyone
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get category by id
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
    }

    // Update category - Admin only
    public Category updateCategory(Integer id, Category updatedData) {
        Category category = getCategoryById(id);
        category.setName(updatedData.getName());
        category.setDescription(updatedData.getDescription());
        category.setIconUrl(updatedData.getIconUrl());
        return categoryRepository.save(category);
    }

    // Delete category - Admin only
    public String deleteCategory(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
        categoryRepository.deleteById(id);
        return "Category deleted successfully!";
    }
}