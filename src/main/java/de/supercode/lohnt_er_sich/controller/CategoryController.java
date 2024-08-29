package de.supercode.lohnt_er_sich.controller;

import de.supercode.lohnt_er_sich.dto.CategoryDTO;
import de.supercode.lohnt_er_sich.entity.Category;
import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category newCategory = categoryService.createCategory(categoryDTO);
        if (newCategory == null) return new ResponseEntity<>(null, HttpStatus.FOUND);
        else return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategorySearchBy(@RequestParam(required = false) String category) {

        if (category != null) {
            Optional<Category> existCategory = categoryService.getCategoryByName(category);
            return existCategory.map(value -> new ResponseEntity<>(List.of(value), HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
        }
        else {
            List<Category> allCategories = categoryService.getAllCategories();
            if (allCategories.isEmpty()) return ResponseEntity.notFound().build();
            else return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

            if (categoryService.removeCategoryById(id)) return ResponseEntity.ok().build();
            else return ResponseEntity.notFound().build();
    }



}
