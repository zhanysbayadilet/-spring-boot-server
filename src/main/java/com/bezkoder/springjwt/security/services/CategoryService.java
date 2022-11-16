package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.repository.CategoryRepository;
import com.bezkoder.springjwt.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(int id) {
        Optional<Category> foundCategory = categoryRepository.findById(id);
        return foundCategory.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
