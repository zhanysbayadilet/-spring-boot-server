package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.CategoryDTO;
import com.bezkoder.springjwt.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }

}
