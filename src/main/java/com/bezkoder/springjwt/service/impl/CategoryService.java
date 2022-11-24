package com.bezkoder.springjwt.service.impl;

import com.bezkoder.springjwt.dto.CategoryDTO;
import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.repository.CategoryRepository;
import com.bezkoder.springjwt.service.ICategoryService;
import com.bezkoder.springjwt.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return ConvertUtils.convertCategoryListToDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = ConvertUtils.convertToCategory(categoryDTO);
        return ConvertUtils.convertToCategoryDTO(categoryRepository.saveAndFlush(category));
    }

    @Override
    public Boolean deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
        return true;
    }

}
