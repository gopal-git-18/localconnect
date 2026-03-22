package com.localconnect.controller;

import com.localconnect.entity.Category;
import com.localconnect.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    // Add category - Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(
            @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    // Get all categories - Everyone
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Get category by id - Everyone
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // Update category - Admin only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @RequestBody Category updatedData) {
        return ResponseEntity.ok(
                categoryService.updateCategory(id, updatedData));
    }

    // Delete category - Admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}