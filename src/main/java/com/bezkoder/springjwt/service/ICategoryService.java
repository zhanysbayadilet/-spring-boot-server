package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategory();

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    Boolean deleteCategoryById(int id);
}
