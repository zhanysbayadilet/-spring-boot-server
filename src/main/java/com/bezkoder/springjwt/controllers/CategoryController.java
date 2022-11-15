package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.CategoryDTO;
import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.security.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<CategoryDTO> getCategories(){
        System.out.println(categoryService.findAll().stream().map(this::convertToCategoryDTO)
                .collect(Collectors.toList()));
        return categoryService.findAll().stream().map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    //convertTo
    public Category convertToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

}
